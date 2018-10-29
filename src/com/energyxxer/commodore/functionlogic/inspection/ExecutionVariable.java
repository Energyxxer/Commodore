package com.energyxxer.commodore.functionlogic.inspection;

public enum ExecutionVariable {
    /**
     * When used, this means the command's execution may vary, depending on which entity sends it.
     * <br>
     *     When modified, this means the command's sender is changed.
     * */
    SENDER,
    /**
     * When used, this means the command's execution may vary, depending on the X coordinate of the execution.
     * <br>
     *     When modified, this means the command execution's X coordinate is changed.
     * */
    X,
    /**
     * When used, this means the command's execution may vary, depending on the Y coordinate of the execution.
     * <br>
     *     When modified, this means the command execution's Y coordinate is changed.
     * */
    Y,
    /**
     * When used, this means the command's execution may vary, depending on the Z coordinate of the execution.
     * <br>
     *     When modified, this means the command execution's Z coordinate is changed.
     * */
    Z,
    /**
     * When used, this means the command's execution may vary, depending on the yaw (y_rotation) of the execution.
     * <br>
     *     When modified, this means the command execution yaw is changed.
     * */
    YAW,
    /**
     * When used, this means the command's execution may vary, depending on the pitch (x_rotation) of the execution.
     * <br>
     *     When modified, this means the command execution pitch is changed.
     * */
    PITCH,
    /**
     * When used, this means the command's execution may vary, depending on which dimension the command is run in.
     * <br>
     *     When modified, this means the command execution dimension is changed.
     * */
    DIMENSION,
    /**
     * When used, this means the command's execution may vary, depending on the anchor (feet or eyes) of the execution.
     * <br>
     *     When modified, this means the command execution anchor is changed. Only applies to commands which utilize
     *     local coordinates.
     * */
    ANCHOR,
    /**
     * When used, this means the command will do different things, depending on how many times it is run. Realistically,
     * this variable is assumed to always be used. As such, there is no need to mark this as used in a usage
     * <code>ExecutionVariableMap</code>, only in modification maps, such as those of execute sub-commands.
     * <br>
     *     When modified, this means the number of executions of the command is changed.
     * */
    COUNT,
    /**
     * When used, this means the command's execution will depend on a condition. Realistically,
     * this variable is assumed to always be used. As such, there is no need to mark this as used in a usage
     * <code>ExecutionVariableMap</code>, only in modification maps, such as those of execute sub-commands.
     * <br>
     *     When modified, this means the command may or may not run. This is practically the same as setting the
     *     count to zero or one, and is modified when conditions are applied, or a selector which could return no
     *     entities is used.
     * */
    CONDITION
}
