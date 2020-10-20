import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Scanner;

public class IterativeDP {


    // main here is just a test/demo program - it's not "the" main for you
    public static int eval(ArrayList a, int start, int end) {
        int evaluate = BlackBox.evaluate(a, start, end);
        return evaluate;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String dir = "C:\\Users\\J1843\\Documents\\GitHub\\problemset 2\\src\\";
        File textDir = new File(dir);
        FilenameFilter filter = new FilenameFilter() {
            public boolean accept(File f, String name) {
                return name.startsWith(args[0]);
            }
        };


        ArrayList<ArrayList> arrayLists = new ArrayList<>();
        for (File file : textDir.listFiles(filter)) {
            Scanner text = new Scanner(file);
            ArrayList<String> strings = new ArrayList<String>();
            while (text.hasNext()) {
                String line = text.nextLine();
                strings.add(line);
            }
            arrayLists.add(strings);
        }
        findOPT(arrayLists);
    }


    public static void findOPT(ArrayList<ArrayList> arrayLists) {
        ArrayList aList = arrayLists.get(0);
        ArrayList bList = arrayLists.get(1);
        ArrayList cList = arrayLists.get(2);

        int current1A;
        int current2A;
        int current3A;
        int current4A;

        int current1B;
        int current2B;
        int current3B;
        int current4B;

        int current1C;
        int current2C;
        int current3C;
        int current4C;

        String currentMAXA = "";
        int MaxOptA = 1;
        String currentMAXB = "";
        int MaxOptB = 3;
        String currentMAXC = "";
        int MaxOptC = 0;
        int aLength = aList.size() - 1;
        int bLength = bList.size() - 1;
        int cLength = cList.size() - 1;


        for (int i = 0; i < arrayLists.size() - 1; i++) {
            ArrayList endSeg = arrayLists.get(i);
            int baseEval = eval(endSeg, 0, endSeg.size() - 1);

            // iterate from 1 to m;
            for (int m = endSeg.size() - 1; m > 0; m--) {

                //int j will be the int connect right after the last segment m to end
                int j = m - 1;
                // case a
                for (int n = 0; n < j; n++) {
//                    System.out.println("ssMaxOptA");
                    if (i == 0) {
                        //System.out.println("case 0");
                        endSeg = aList;
                        while (m == 1) {
                            current1A = eval(endSeg, m, aLength) + eval(bList, 0, 0);
                            //System.out.println(MaxOptA);
                            current2A = eval(endSeg, m, aLength) + eval(cList, 0, 0);
                            if (Math.max(current1A, current2A) > baseEval) {
                                if (current1A > current2A) {
                                    MaxOptA = current1A;
                                } else {
                                    MaxOptA = current2A;
                                   // System.out.println(MaxOptA);
                                }
                            }
                        } while (n == 0) {
                            current1A = eval(endSeg, m, aLength) + eval(bList, 1, j) + eval(cList, 0, 0);
                            current2A = eval(endSeg, m, aLength) + eval(cList, 1, j) + eval(bList, 0, 0);
                           // System.out.println(current1A);
                            if (Math.max(current1A, current2A) > baseEval) {
                                if (current1A > current2A) {
                                    MaxOptA = current1A;
                                } else {
                                    MaxOptA = current2A;
                                }
                            }
                        }
                        while (n > 0) {
                            current1A = eval(endSeg, m, aLength) + eval(bList, 0, n);
                            current2A = eval(endSeg, m, aLength) + eval(cList, 0, n);
//                            System.out.println(current1A);
                            current3A = eval(endSeg, m, aLength) + eval(bList, n, j) + eval(cList, 0, n - 1);
                            current4A = eval(endSeg, m, aLength) + eval(cList, n, j) + eval(bList, 0, n - 1);
                            if (Math.max(current1A, current2A) > baseEval
                                    && Math.max(current3A, current4A) > baseEval) {
                                if (Math.max(current1A, current2A) > Math.max(current3A, current4A)) {
                                    if (current1A > current2A) {
                                        MaxOptA = current1A;
                                    } else {
                                        MaxOptA = current2A;
                                    }
                                } else {
                                    if (current3A > current4A) {
                                        MaxOptA = current3A;
                                    } else {
                                        MaxOptA = current4A;
                                    }
                                }
                            }
                        }
                    }


                    // case b
                    while (i == 1) {
                        endSeg = bList;
                        while (m == 1) {
                            current1B = eval(endSeg, m, bLength) + eval(aList, 0, 0);
                            current2B = eval(endSeg, m, bLength) + eval(cList, 0, 0);
                            if (Math.max(current1B, current2B) > baseEval) {
                                if (current1B > current2B) {
                                    MaxOptB = current1B;
                                } else {
                                    MaxOptB = current2B;
                                }
                            }
                        }
                        while (n == 0) {
                            current1B = eval(endSeg, m, bLength) + eval(aList, 1, j) + eval(cList, 0, 0);
                            current2B = eval(endSeg, m, bLength) + eval(cList, 1, j) + eval(aList, 0, 0);
                            if (Math.max(current1B, current2B) > baseEval) {
                                if (current1B > current2B) {
                                    MaxOptB = current1B;
                                } else {
                                    MaxOptB = current2B;
                                }
                            }
                        }
                        while (n > 0) {
                            current1B = eval(endSeg, m, bLength) + eval(aList, 0, n);
                            current2B = eval(endSeg, m, bLength) + eval(cList, 0, n);
                            current3B = eval(endSeg, m, bLength) + eval(aList, n, j) + eval(cList, 0, n - 1);
                            current4B = eval(endSeg, m, bLength) + eval(cList, n, j) + eval(aList, 0, n - 1);
                            if (Math.max(current1B, current2B) > baseEval && Math.max(current3B, current4B) > baseEval) {
                                if (Math.max(current1B, current2B) > Math.max(current3B, current4B)) {
                                    if (current1B > current2B) {
                                        MaxOptB = current1B;
                                    } else {
                                        MaxOptB = current2B;
                                    }
                                } else {
                                    if (current3B > current4B) {
                                        MaxOptB = current3B;
                                    } else {
                                        MaxOptB = current4B;
                                    }
                                }
                            }
                        }
                    }
                    // case c
                    if (i == 2) {
                        endSeg = cList;
                        if (m == 1) {
                            current1C = eval(endSeg, m, cLength) + eval(aList, 0, 0);
                            current2C = eval(endSeg, m, cLength) + eval(bList, 0, 0);
                            if (Math.max(current1C, current2C) > baseEval) {
                                if (current1C > current2C) {
                                    MaxOptC = current1C;
                                } else {
                                    MaxOptC = current2C;
                                }
                            }
                        } else if (n == 0) {
                            current1C = eval(endSeg, m, cLength) + eval(aList, 1, j) + eval(bList, 0, 0);
                            current2C = eval(endSeg, m, cLength) + eval(bList, 1, j) + eval(aList, 0, 0);
                            if (Math.max(current1C, current2C) > baseEval) {
                                if (current1C > current2C) {
                                    MaxOptC = current1C;
                                } else {
                                    MaxOptC = current2C;
                                }
                            }
                        } else {
                            current1C = eval(endSeg, m, bLength) + eval(aList, 0, n);
                            current2C = eval(endSeg, m, bLength) + eval(bList, 0, n);
                            current3C = eval(endSeg, m, bLength) + eval(aList, n, j) + eval(bList, 0, n - 1);
                            current4C = eval(endSeg, m, bLength) + eval(bList, n, j) + eval(aList, 0, n - 1);
                            if (Math.max(current1C, current2C) > baseEval && Math.max(current3C, current4C) > baseEval) {
                                if (Math.max(current1C, current2C) > Math.max(current3C, current4C)) {
                                    if (current1C > current2C) {
                                        MaxOptC = current1C;
                                    } else {
                                        MaxOptC = current2C;
                                    }
                                } else {
                                    if (current3C > current4C) {
                                        MaxOptC = current3C;
                                    } else {
                                        MaxOptC = current4C;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (MaxOptA > MaxOptB && MaxOptA > MaxOptC) {
                System.out.println(currentMAXA);
                System.out.println(MaxOptC);
            } else if (MaxOptB > MaxOptA && MaxOptB > MaxOptC) {
                System.out.println(currentMAXB);
            } else if (MaxOptC > MaxOptB && MaxOptC > MaxOptA) {
                System.out.println(currentMAXC);
            }
        System.out.println(MaxOptA);
    }
}


//                int test = eval(cList, 0, 0) + eval(bList, 1, 1) + eval(aList, 2, 2);
//                System.out.println(test);
//                System.out.println(MaxOptA);
//                System.out.println(MaxOptB);
//                System.out.println(MaxOptC);


//        // iterate from 1 to m;
//        for (int i = 0; i < arrayLists.size() - 1; i++) {
//            ArrayList endSeg = arrayLists.get(i);
//            int baseEval = eval(endSeg,2,2);
//            for (int m = 1; m < endSeg.size() - 1; m++) {
//                int j = m - 1;
//                //iterate from 1 to n; where n <= m
//                for (int n = 0; n < m; n++) {
//
//
//                    //case a
//                    if (i == 0) {
//
//
//                        if (m != 2) {
//                            current1A = eval(aList, m, aLength) + eval(bList, n + 1, m - 1) + eval(cList, 0, n);
//                            current2A = eval(aList, m, aLength) + eval(cList, n + 1, m - 1) + eval(bList, 0, n);
//                        } else {
//                            current1A = eval(aList, m, aLength) + eval(bList, 0, n);
//                            current2A = eval(aList, m, aLength) + eval(cList, 0, n);
//                        }
//                        if (current1A > MaxOptA || current2A > MaxOptA) {
//                            if (current1A > current2A) {
//
//                                MaxOptA = current1A;
//                            } else {
//                                MaxOptA = current2A;
//                            }
//                        } else {
//                            MaxOptA = baseEval;
//                        }
//
//
//                    }
//
//
//
//
//                    else if (i == 1) {
//                        if (m == 2 && n == 1) {
//                            current1B = eval(bList, m, bLength) + eval(aList, n, m) + eval(cList, 0, n);
//                            current2B = eval(bList, m, bLength) + eval(cList, n, m) + eval(aList, 0, n);
//                            current3B = eval(bList, m, bLength) + eval(aList, 0, n);
//                            current4B = eval(bList, m, bLength) + eval(cList, 0, n);
//                            if (current1B > MaxOptB || current2B > MaxOptB || current1B > MaxOptB || current2B > MaxOptB) {
//                                if (current1B > Math.max(current2B, current3B)) {
//                                    currentMAXB = "Camera B from" + m + "to" + bLength + "\t" +
//                                            "Camera A from" + n + "to" + m + "\t" +
//                                            "Camera C from" + 0 + "to" + n + "\t";
//                                    MaxOptB = current1B;
//                                } else {
//                                    currentMAXB = "Camera B from" + m + "to" + bLength + "\t" +
//                                            "Camera C from" + n + "to" + m + "\t" +
//                                            "Camera A from" + 0 + "to" + n + "\t";
//                                    MaxOptB = current2B;
//                                }
//                            }
//                        } else {
//                            current1B = eval(bList, m, bLength) + eval(aList, n + 1, m - 1) + eval(cList, 0, n);
//                            current2B = eval(bList, m, bLength) + eval(cList, n + 1, m - 1) + eval(aList, 0, n);
//                            if (current1B > MaxOptB || current2B > MaxOptB) {
//                                if (current1B > current2B) {
//                                    currentMAXB = "Camera B from" + m + "to" + bLength + "\t" +
//                                            "Camera A from" + n + "to" + m + "\t" +
//                                            "Camera C from" + 0 + "to" + n + "\t";
//                                    MaxOptB = current1B;
//                                } else {
//                                    currentMAXB = "Camera B from" + m + "to" + bLength + "\t" +
//                                            "Camera C from" + n + "to" + m + "\t" +
//                                            "Camera A from" + 0 + "to" + n + "\t";
//                                    MaxOptB = current2B;
//                                }
//                            }
                            //                        else {
//                             currentMAXB = "Camera B from" + 0 + "to" + maxLengthB;
//                             MaxOptB = baseEval;
//                        }
                        //}

//                    } else if (i == 2) {
//
//                        if (m != 2) {
//                            current1A = eval(aList, m, aLength) + eval(bList, n + 1, m - 1) + eval(cList, 0, n);
//                            current2A = eval(aList, m, aLength) + eval(cList, n + 1, m - 1) + eval(bList, 0, n);
//                        } else {
//                            current1A = eval(aList, m, aLength) + eval(bList, 0, n);
//                            current2A = eval(aList, m, aLength) + eval(cList, 0, n);
//                        }
//                        if (current1A > MaxOptA || current2A > MaxOptA) {
//                            if (current1A > current2A) {
//
//                                MaxOptA = current1A;
//                            } else {
//                                MaxOptA = current2A;
//                            }
//                        } else {
//                            MaxOptA = baseEval;
//                        }
//                    }

//                        // case c
//                    } else {
//                        endSeg = cList;
//                        if (m == 1) {
//                            current1C = eval(endSeg, m, cLength) + eval(aList, 0, 0);
//                            current2C = eval(endSeg, m, cLength) + eval(bList, 0, 0);
//                            if (Math.max(current1C, current2C) > baseEval) {
//                                if (current1C > current2C) {
//                                    MaxOptC = current1C;
//                                } else {
//                                    MaxOptC = current2C;
//                                }
//                            }
//                        } else if (n == 0) {
//                            current1C = eval(endSeg, m, cLength) + eval(aList, 1, j) + eval(bList, 0, 0);
//                            current2C = eval(endSeg, m, cLength) + eval(bList, 1, j) + eval(aList, 0, 0);
//                            if (Math.max(current1C, current2C) > baseEval) {
//                                if (current1C > current2C) {
//                                    MaxOptC = current1C;
//                                } else {
//                                    MaxOptC = current2C;
//                                }
//                            }
//                        } else {
//                            current1C = eval(endSeg, m, bLength) + eval(aList, 0, n);
//                            current2C = eval(endSeg, m, bLength) + eval(bList, 0, n);
//                            current3C = eval(endSeg, m, bLength) + eval(aList, n, j) + eval(bList, 0, n - 1);
//                            current4C = eval(endSeg, m, bLength) + eval(bList, n, j) + eval(aList, 0, n - 1);
//                            if (Math.max(current1C, current2C) > baseEval && Math.max(current3C, current4C) > baseEval) {
//                                if (Math.max(current1C, current2C) > Math.max(current3C, current4C)) {
//                                    if (current1C > current2C) {
//                                        MaxOptC = current1C;
//                                    } else {
//                                        MaxOptC = current2C;
//                                    }
//                                } else {
//                                    if (current3C > current4C) {
//                                        MaxOptC = current3C;
//                                    } else {
//                                        MaxOptC = current4C;
//                                    }
//                                }
//                            }
//                        }
//                    }
//
//
//
//
//

//
//                }
//            }
//        }
//        System.out.println(MaxOptA);
//        System.out.println(MaxOptB);
//        System.out.println(MaxOptC);
//    }
//}






//


//            }
//        }
//    }
//}