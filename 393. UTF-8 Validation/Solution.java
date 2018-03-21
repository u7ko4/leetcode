public class Solution {

    public boolean validUtf8(int[] data) {
        if (data == null || data.length == 0) return false;
        for (int i = 0; i < data.length; i++) {
            if (data[i] < 0 || data[i] > 255) return false;
            int bytes = 0;
            if ((data[i] & 0x80) == 0) {
                // 0xxxxxxx
                bytes = 1;
            } else if ((data[i] & 0xE0) == 0xC0) {
                // 110xxxxx 10xxxxxx
                bytes = 2;
            } else if ((data[i] & 0xF0) == 0xE0) {
                // 1110xxxx 10xxxxxx 10xxxxxx
                bytes = 3;
            } else if ((data[i] & 0xF8) == 0xF0) {
                // 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
                bytes = 4;
            } else {
                return false;
            }
            for (int j = 1; j < bytes; j++) {
                if (i + j >= data.length) return false;
                // 10xxxxxx
                if ((data[i + j] & 0xC0) != 0x80) return false;
            }
            i = i + bytes - 1;
        }
        return true;
    }
}