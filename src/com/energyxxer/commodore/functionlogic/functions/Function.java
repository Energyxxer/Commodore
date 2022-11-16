package com.energyxxer.commodore.functionlogic.functions;

import com.energyxxer.commodore.Commodore;
import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.module.Exportable;
import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.module.Namespaced;
import com.energyxxer.commodore.module.settings.ModuleSettingsManager;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

public class Function implements FunctionSection, Exportable, Namespaced {
    public static final String ALLOWED_PATH_REGEX = "[a-z0-9_/.-]+";

    @NotNull
    private final FunctionManager parent;

    @NotNull
    private final Namespace namespace;
    @NotNull
    private final String path;
    @NotNull
    private final ArrayList<@NotNull FunctionWriter> content = new ArrayList<>();

    @NotNull
    private ExecutionContext execContext;
    private boolean contentResolved = false;
    private String resolvedContent = null;

    private boolean export = true;

    Function(@NotNull FunctionManager parent, @NotNull Namespace namespace, @NotNull String path) {
        this(parent, namespace, path, null);
    }

    Function(@NotNull FunctionManager parent, @NotNull Namespace namespace, @NotNull String path, @Nullable Entity sender) {
        this.parent = parent;

        this.namespace = namespace;

        path = path.toLowerCase(Locale.ENGLISH);
        this.path = path;

        if (!path.matches(ALLOWED_PATH_REGEX)) {
            throw new CommodoreException(CommodoreException.Source.FORMAT_ERROR, "Illegal function path: " + path);
        }

        this.execContext = new ExecutionContext();
    }

    @Override
    public void append(@NotNull Collection<@NotNull FunctionWriter> writers) {
        this.content.addAll(writers);
        writers.forEach(w -> w.onAppend(this));
        contentResolved = false;
    }

    @Override
    public void prepend(@NotNull Collection<@NotNull FunctionWriter> writers) {
        this.content.addAll(0, writers);
        writers.forEach(w -> w.onAppend(this));
        contentResolved = false;
    }

    @NotNull
    public String getFullName() {
        return namespace.toString() + VersionFeatureManager.getString("function.namespace_separator", ":") + path;
    }

    @NotNull
    public Namespace getNamespace() {
        return namespace;
    }

    @NotNull
    public String getPath() {
        return path;
    }

    @NotNull
    @Override
    public String getExportPath() {
        return VersionFeatureManager.getString("function.export_path") + getPath() + VersionFeatureManager.getString("function.extension");
    }

    @NotNull
    public FunctionManager getParent() {
        return parent;
    }

    @NotNull
    public String getResolvedContent() {
        if(!contentResolved) {
            StringBuilder sb = new StringBuilder();

            for(FunctionWriter writer : content) {
                writer.assertAvailable();
                String content = writer.toFunctionContent(this);
                sb.append(content);
                sb.append('\n');
            }
            resolvedContent = sb.toString();
            contentResolved = true;
        }
        return resolvedContent;
    }

    public ExecutionContext getExecutionContext() {
        return execContext;
    }

    public void setExecutionContext(ExecutionContext execContext) {
        if(content.isEmpty()) {
            this.execContext = execContext;
        } else throw new IllegalStateException("Cannot change function execution context when it already has entries");
    }

    @NotNull
    public String getHeader() {
        return "[Function " + getFullName() + " : " + content.size() + " " + ((content.size() == 1) ? "entry" : "entries") + "]";
    }

    public int getEntryCount() {
        return content.size();
    }

    @Override
    public boolean shouldExport() {
        return export && (!content.isEmpty() || ModuleSettingsManager.getActive().EXPORT_EMPTY_FUNCTIONS.getValue());
    }

    @Override
    public void setExport(boolean export) {
        this.export = export;
    }

    @NotNull
    @Override
    public byte[] getContents() {
        return getResolvedContent().getBytes(Commodore.getDefaultEncoding());
    }

    @Override
    public void dispose() {
        resolvedContent = null;
        contentResolved = false;
    }

    @Override
    public String toString() {
        return getHeader();
    }
}
