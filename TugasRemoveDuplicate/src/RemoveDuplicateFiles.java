import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class RemoveDuplicateFiles {

    public static void main(String[] args) {
        String folderPath = "/Users/muhammadazhar/Library/CloudStorage/OneDrive-Personal/TesJava";
        removeDuplicates(folderPath);
    }

    private static void removeDuplicates(String folderPath) {
        File folder = new File(folderPath);

        if (!folder.isDirectory()) {
            System.out.println("Invalid folder path.");
            return;
        }

        File[] files = folder.listFiles();

        if (files == null || files.length == 0) {
            System.out.println("Folder is empty.");
            return;
        }

        // Sort files based on last modified timestamp in ascending order
        Arrays.sort(files, Comparator.comparingLong(File::lastModified));

        System.out.println("Removing duplicate files and keeping the shortest named file:");

        for (int i = 0; i < files.length - 1; i++) {
            for (int j = i + 1; j < files.length; j++) {
                if (files[i].isFile() && files[j].isFile() && files[i].length() == files[j].length()) {
                    // If duplicates are found, keep the file with the shortest name
                    File fileToKeep = files[i].getName().length() <= files[j].getName().length() ? files[i] : files[j];
                    File fileToDelete = files[i].equals(fileToKeep) ? files[j] : files[i];

                    System.out.println("Duplicate found: " + fileToDelete.getAbsolutePath());
                    fileToDelete.delete();
                }
            }
        }

        System.out.println("Duplicate removal completed.");
    }
}
