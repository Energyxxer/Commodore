package com.energyxxer.commodore.score.operations;

import com.energyxxer.commodore.functions.Function;
import com.energyxxer.commodore.functions.FunctionWriter;

@Deprecated
public abstract class ScoreHolderOperation implements FunctionWriter {

    public enum AccessType {
        /** Is used exclusively for instructions that read the contents of a score reference.
         * These instructions should be validate previous write and adjust operations inside a <link>LocalScore</link> buffer
         * */
        READ(true),
        /** Is used for instructions that override the already-existing score in an idempotent manner. */
        OVERWRITE(false),
        /** Is used for instructions that modify the score that already exists, in a non-idempotent manner*/
        ADJUST(false);

        private final boolean defaultWriteAction;

        AccessType(boolean defaultWriteAction) {
            this.defaultWriteAction = defaultWriteAction;
        }

        public boolean getDefaultWriteAction() {
            return defaultWriteAction;
        }
    }

    protected final LocalScore score;

    protected AccessType access;
    protected boolean used;

    public ScoreHolderOperation(AccessType access, LocalScore score) {
        this.score = score;

        this.access = access;
        this.used = access.getDefaultWriteAction();
    }

    public AccessType getAccess() {
        return access;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public abstract String getOperationContent(Function function);

    @Override
    public String toFunctionContent(Function function) {
        return (used) ? getOperationContent(function) : null;
    }

    @Override
    public void onAppend() {
        score.filter(this);
    }
}
