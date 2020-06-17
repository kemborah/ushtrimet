package com.company;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws Exception {

        String rules = "CardNo..n18~ExpDaten4~Amountf~ZIPn5|0~Promotion..c10|0~Email..c50|0";
        String[] rulesArray = rules.split("~");

        ArrayList<Rules> rregullat = new ArrayList<>();
        String nameOfField = "";
        String lengthStr;
        int length = 0;

        boolean isLengthVariable = false;
        boolean isRequired = true;
        char type;
        int k;
        Pattern p = Pattern.compile("\\d+");
        Matcher m;
        for (int i = 0; i <= rulesArray.length - 1; i++) {
            if (rulesArray[i].contains("..")) {
                isLengthVariable = true;
            }

            m = p.matcher(rulesArray[i]);
            if (m.find()) {
                lengthStr = m.group();
                length = Integer.parseInt(lengthStr);
                k = rulesArray[i].indexOf(lengthStr) - 1;
                nameOfField = rulesArray[i].substring(0, k);
                type = rulesArray[i].charAt(k);
                if (nameOfField.endsWith("..")) {
                    nameOfField = nameOfField.substring(0, nameOfField.length() - 2);
                }

            } else {
                type = rulesArray[i].charAt(rulesArray.length);
                nameOfField = rulesArray[i].substring(0, nameOfField.length() - 1);
            }

            if (rulesArray[i].contains("|0")) {
                isRequired = false;
            }
            rregullat.add(new Rules(nameOfField, length, isLengthVariable, isRequired, type));
        }

        Scanner in = new Scanner(new FileReader("./input.txt"));
        PrintStream out = new PrintStream(new FileOutputStream("./output.txt", true));
        System.setOut(out);
        String a;
        boolean gabim = false;
        String[] arrOfStr;

        while (in.hasNextLine()) {
            a = in.nextLine();
            //System.out.println(a);
            arrOfStr = a.split("~");

            try {
                if (arrOfStr.length != rregullat.size())
                    throw new Exception("Line" + a + "Number of total fields for the transaction are not correct (less or more fields than required)");
            } catch (Exception e) {
                e.printStackTrace();
            }
            int j = 0;
            int i = 0;
            String ok = "{";
            while (i < arrOfStr.length && j < rregullat.size()) {
                gabim = false;
                if (rregullat.get(j).getType() == 'n') {
                    if (rregullat.get(j).getNameOfField() == "ExpDate") {
                        if (!ExpDateValidator.validate(arrOfStr[i])) {
                            gabim = true;
                        }

                    } else {
                        if (!StringOfNumsValidator.validate(arrOfStr[i], rregullat.get(j).getLength(), rregullat.get(j).isLengthVariable())) {
                            if (rregullat.get(j).isRequired()) {
                                gabim = true;
                            } else {
                                ok += "\"" + rregullat.get(j).getNameOfField() + "\":\"" + "\",";
                                j++;
                            }
                        } else {
                            ok += "\"" + rregullat.get(j).getNameOfField() + "\":\"" + arrOfStr[i] + "\",";
                            i++;
                            j++;
                        }
                    }
                } else if (rregullat.get(j).getType() == 'c') {
                    if (!StringOfCharsValidator.validate(arrOfStr[i], rregullat.get(j).getLength(), rregullat.get(j).isLengthVariable())) {
                        if (rregullat.get(j).isRequired()) {
                            gabim = true;
                        } else {
                            ok += "\"" + rregullat.get(j).getNameOfField() + "\":\"" + "\",";
                            j++;
                        }
                    } else {
                        ok += "\"" + rregullat.get(j).getNameOfField() + "\":\"" + arrOfStr[i] + "\",";
                        j++;
                        i++;
                    }
                } else {
                    if (!FloatValidator.validate(arrOfStr[i])) {
                        if (rregullat.get(j).isRequired()) {
                            gabim = true;
                        } else {
                            ok += "\"" + rregullat.get(j).getNameOfField() + "\":\"" + "\",";
                            j++;
                        }

                    } else {
                        ok += "\"" + rregullat.get(j).getNameOfField() + "\":\"" + arrOfStr[i] + "\",";
                        i++;
                        j++;
                    }

                }
                try {
                    if (gabim)
                        throw new Exception("Line" + a + "Missing required field");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    if (rregullat.get(j).getLength() - 1 != arrOfStr[i].length() - 1)
                        throw new Exception("Field" + arrOfStr[i] + " Wrong Field Length");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (gabim == true) {
                    break;
                }
            }

            ok = ok.substring(0, ok.length() - 1);
            ok += "}";
            if (gabim == false) {
                System.out.println(ok);
            }

        }
    }
}
