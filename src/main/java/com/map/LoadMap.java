package com.map;

import com.hs.ConstGame;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * class quản lý việc load map.
 */
public class LoadMap {


    private static Maplevel map;

    /**
     * hàm dùng để test.
     *
     * @return MapLevel 1.
     */
    public static Maplevel load() {
        return load(1);
    }

    /**
     * load map từ file txt.
     *
     * @param level level cần load.
     * @return MapLevel dc khởi tạo dựa trên các thông số đó.
     */
    public static Maplevel load(int level) {
        try {

            Scanner sc = new Scanner(new FileInputStream(String.format(ConstGame.InfoGame.PATH_LOAD_MAP, level)));

            map = new Maplevel(sc.nextInt(), sc.nextInt(), sc.nextInt());
            sc.nextLine();
            String tem;
            for (int t = 0; t < map.y; t++) {
                tem = sc.nextLine();
//                System.out.println(tem);
                for (int i = 0; i < tem.length(); i++) {
                    map.mapChar[t][i] = tem.charAt(i);
                }
            }

            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return map;
    }


}
