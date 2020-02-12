package org.sct.invitecode.enumeration;

import lombok.Getter;

public enum LangType {

    COMMANG_HELP("Language.CommandHelp"),
    OP_COMMANG_HELP("Language.OPCommandHelp");


    @Getter String path;

    private LangType(String path) {
        this.path = path;
    }
}
