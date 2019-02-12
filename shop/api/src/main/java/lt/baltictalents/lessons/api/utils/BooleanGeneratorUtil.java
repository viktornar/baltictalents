package lt.baltictalents.lessons.api.utils;

import java.util.Random;

import lombok.val;

public class BooleanGeneratorUtil {
    static public boolean generateRandomBoolean() {
        val random = new Random();

        if ((random.nextInt((10 - 1) + 1) + 1) % 2 == 1) {
            return true;
        }

        return false;
    }
}