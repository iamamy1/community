package com.nowcoder.community;

import java.io.IOException;

import static java.lang.Thread.sleep;

public class WkTests {
    public static void main(String[] args) {
        String cmd = "/usr/local/bin/wkhtmltoimage --quality 75 https://www.nowcoder.com /Users/amelia/IdeaProjects/data/wk-images/3.png";
        try {
            Process p = Runtime.getRuntime().exec(cmd);
            if(p.waitFor()==0){
                System.out.println("ok.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
