package hashcode;

import java.util.*;
import java.io.*;

public class Solution {

    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
            int Books = in.nextInt();
            int Libraries = in.nextInt();
            int Days = in.nextInt();
            int[] books = new int[Books];
            for (int i = 0; i < Books; i++) {
                books[i] = in.nextInt();
            }

            int[] signedUpLibraries = new int[Libraries];
            int[][] booksSentByEachLibrary = new int[Libraries][];
            int[][] booksInEachLibrary = new int[Libraries][];
            int[] signupDaysInEachLibrary = new int[Libraries];
            int[] canShipBooksInLibrary = new int[Libraries];
            int[] totalBooksInEachLibrary = new int[Libraries];
            int[] totalBooksSentByEachLibrary = new int[Libraries];
            Arrays.fill(totalBooksInEachLibrary, 0);

            for (int i = 0; i < Libraries; i++) {
                int currentLibraryBooks = in.nextInt();
                booksInEachLibrary[i] = new int[currentLibraryBooks];
                booksSentByEachLibrary[i] = new int[currentLibraryBooks];
                signupDaysInEachLibrary[i] = in.nextInt();
                canShipBooksInLibrary[i] = in.nextInt();
                for (int j = 0; j < currentLibraryBooks; j++) {
                    booksInEachLibrary[i][j] = in.nextInt();
                    totalBooksInEachLibrary[i]++;
                }
            }

            boolean isSigningUp = false, isReadyToShip = false;
            int signupDaysPassed = 0, numberOfSignupDaysNeeded = 0, totalSignedUpLibraries = 0;
            int signingUpLibrary = 0, signedUp = 0, shipping = 0;
            int[] currentLibraries = new int[Libraries];
            int[] previousSentBooks = new int[Libraries];
            Arrays.fill(previousSentBooks, 0);
            for (int i = 0; i < Days; i++) {
                if (isSigningUp == false && totalSignedUpLibraries != Libraries) {
                    isSigningUp = true;
                    numberOfSignupDaysNeeded = signupDaysInEachLibrary[signingUpLibrary];
                }

                if (totalSignedUpLibraries > 0) {
                    for (int j = 0; j < totalSignedUpLibraries; j++) {
                        int current = signedUpLibraries[j];
                        if (previousSentBooks[current] < booksInEachLibrary[current].length) {
                            for (int k = 0; k < canShipBooksInLibrary[current]; k++) {
                                if (previousSentBooks[current] < booksInEachLibrary[current].length) {
                                    previousSentBooks[current]++;
                                    totalBooksSentByEachLibrary[current]++;
                                }

                            }
                        }
                    }

                }

                if (signupDaysPassed + 1 == numberOfSignupDaysNeeded) {
                    signedUpLibraries[signedUp++] = signingUpLibrary;
                    totalSignedUpLibraries++;
                    isSigningUp = false;
                    isReadyToShip = true;
                    signingUpLibrary++;
                    signupDaysPassed = 0;
                }
                if (isSigningUp) {
                    signupDaysPassed++;
                }
                //System.out.println();
            }

            System.out.println(totalSignedUpLibraries);
            for (int i = 0; i < totalSignedUpLibraries; i++) {
                System.out.print(i + " " + totalBooksSentByEachLibrary[i] + "\n");
                for (int j = 0; j < totalBooksSentByEachLibrary[i]; j++) {
                    System.out.print(booksInEachLibrary[i][j] + " ");
                }
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
