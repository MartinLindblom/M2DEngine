package Miscellaneous;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

public class Logger
{
    private static OutputStream outputStream = System.out;



    public static void setOutputStream(OutputStream outputStream)
    {
        try
        {
            outputStream.write(("Testing stream!").getBytes());

            Logger.outputStream = outputStream;
        }
        catch (IOException e)
        {
            log("Could not change output stream!");
        }
    }

    public static void log(String message)
    {
        try
        {
            outputStream.write((message + "\n").getBytes());
        }
        catch (IOException e)
        {
            System.out.println("Failed to log message: " + message + "\n" + getStackTraceAsString(e));
        }
    }

    public static void logError(String message, Exception ex)
    {
        try
        {
            outputStream.write((message + "\n" + getStackTraceAsString(ex) + "\n").getBytes());
        }
        catch (IOException e)
        {
            System.out.println("Failed to log error: " + message + "\n" + getStackTraceAsString(e));
        }
    }



    private static String getStackTraceAsString(Exception ex)
    {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter, true);
        ex.printStackTrace(printWriter);

        return stringWriter.getBuffer().toString();
    }
}
