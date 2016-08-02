package stone.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

/**
 * Created by JGabrielFreitas on 02/08/16.
 */
public class StringUtils {

    public static String paddingRight(String toPad, int count) {
        return String.format("%1$-" + count + "s", toPad);
    }

    public static String removeNonNumeric(String toClear) {
        if (toClear != null)
            return toClear.replaceAll("[^0-9]", "");
        else
            return toClear;
    }

    public static boolean isNullOrEmpty(String value) {

        return isNull(value) || isEmpty(value);
    }

    public static boolean isValid(String value) {
        return !isNullOrEmpty(value);
    }

    public static boolean isNull(String value) {
        return value == null;
    }

    public static boolean isEmpty(String value) {
        return value.replaceAll("\\s+","").equals(""); // value.equals("") = empty string
    }

    public static String captalize(String givenString) {

        String[] arr = givenString.split(" ");
        StringBuffer stringBuffer = new StringBuffer();

        for (int i = 0; i < arr.length; i++)
            stringBuffer.append(Character.toUpperCase(arr[i].charAt(0))).append(arr[i].substring(1)).append(" ");

        return stringBuffer.toString().trim();
    }

    public static byte[] compress(String stringToCompress) throws Exception {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        OutputStream out = new DeflaterOutputStream(baos);
        out.write(stringToCompress.getBytes("UTF-8"));
        out.close();
        return baos.toByteArray();
    }

    public static String decompress(byte[] bytes) throws Exception {

        InputStream in = new InflaterInputStream(new ByteArrayInputStream(bytes));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[8192];
        int len;

        while ((len = in.read(buffer)) > 0)
            baos.write(buffer, 0, len);

        return new String(baos.toByteArray(), "UTF-8");
    }

}
