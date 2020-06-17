package com.company;

public class Rules {
private String nameOfField;
private int length;
private boolean isLengthVariable;
private boolean required;
private char type;

    public Rules(String nameOfField, int length, boolean isLengthVariable, boolean required, char type) {
        this.nameOfField = nameOfField;
        this.length = length;
        this.isLengthVariable = isLengthVariable;
        this.required = required;
        this.type=type;
    }

    @Override
    public String toString() {
        return "Rules{" +
                "nameOfField='" + nameOfField + '\'' +
                ", length=" + length +
                ", isLengthVariable=" + isLengthVariable +
                ", required=" + required +
                ", type=" + type +
                '}';
    }

    public char getType() {
        return type;
    }

    public String getNameOfField() {
        return nameOfField;
    }

    public int getLength() {
        return length;
    }

    public boolean isLengthVariable() {
        return isLengthVariable;
    }

    public boolean isRequired() {
        return required;
    }
}
