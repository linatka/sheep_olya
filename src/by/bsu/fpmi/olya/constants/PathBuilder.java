package by.bsu.fpmi.olya.constants;

import java.io.File;

/**
 * Created by Lenovo on 03.05.2016.
 */
public class PathBuilder {
    public static String buildPath(String ...args){
        if (args.length != 0){
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < args.length - 1; i++){
                sb.append(args[i]);
                sb.append(File.separator);
            }
            sb.append(args[args.length - 1]);
            return sb.toString();
        }
        return "";
    }
}
