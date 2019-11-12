public enum LexTypes {
    PROGRAM {//TODO add other tokens
        /**
         * TODO Checks the string for occurrence of the token "Program"
         * @param str
         * @return
         */
        public boolean checkStr(String str) {
            return true;
        }
    };

    public abstract boolean checkStr(String str);

}

