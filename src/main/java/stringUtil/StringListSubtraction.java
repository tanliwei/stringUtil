package stringUtil;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by tanlw on 2017-3-20.
 */
public class StringListSubtraction
{
    public static final String EXIT = "N";

    public static void main(String[] args)
    {

        boolean exits;
        do
        {
            String source = getInput("Input source file(File A)");
            String target = getInput("Input target file(File B)");
            File sourceFile = new File(source);
            File targetFile = new File(target);
            Set sourceSet = new HashSet();
            try
            {
                List<String> sourceList = FileUtils.readLines(sourceFile);
                List<String> targetList = FileUtils.readLines(targetFile);
                System.out.println("Source file(File A), lines:" + sourceList.size());
                System.out.println("Target file(File B), lines:" + targetList.size());
                for (int i = 0; i < sourceList.size(); i++) {
                    sourceSet.add(changeStr((String)sourceList.get(i)));
                }
                for (int i = 0; i < targetList.size(); i++) {
                    sourceSet.remove(changeStr(((String)targetList.get(i)).trim()));
                }
                System.out.println("Result file, lines:" + sourceSet.size());
                Iterator iterator = sourceSet.iterator();
                String res = "";
                while (iterator.hasNext()) {
                    res = res + iterator.next() + "\r\n";
                }
                System.out.println("Creating file " + source + "res");
                File sourceFileRes = new File(source + "res");
                sourceFileRes.createNewFile();
                FileUtils.write(sourceFileRes, res);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            exits = exit();
        } while (!exits);
    }

    private static void welcome()
    {
        System.out.println("Content of file A substract content of file B(dereplication,  uppercase then compare)");
    }

    public static String changeStr(String str)
    {
        return str.trim().toUpperCase();
    }

    public static String getInput(String alert)
    {
        String str = null;
        while (null == str)
        {
            System.out.println(alert);

            Scanner sc = new Scanner(System.in);
            str = sc.nextLine();
            if ((null == str) || (str.length() == 0))
            {
                System.out.println("Illegal input");
                str = null;
            }
        }
        return str;
    }

    private static boolean exit()
    {
        String str = getExit();
        if ("N".equals(str.toUpperCase()))
        {
            System.out.println("Exited!");
            return true;
        }
        return false;
    }

    public static String getExit()
    {
        String str = null;
        while (null == str)
        {
            System.out.println("Are you continue?(n/N?)");

            Scanner sc = new Scanner(System.in);
            str = sc.nextLine();
            if ((null == str) || (str.length() == 0))
            {
                System.out.println("Illegal input");
                str = null;
            }
        }
        return str;
    }
}
