import java.util.*;

public class Main {
    public static void main(String[] args) {
        String[] arr = {"Pendanaan", "Terproteksi", "Untuk", "Dampak", "Berarti"};
        String[] arr2 = {"Abc", "bCd"};
        String[] arr3 = {"Oke", "One"};
        String result = charGrouping(arr);
        System.out.println(result);
    }

    public static String charGrouping(String[] arr){
        Map<Character, Integer> dict = new HashMap<>();

        // inisiasi value ke map dict, dengan key character dari kata dan valuenya frekuensi character itu muncul
        for (String word : arr){
            int len = word.length();
            for (int i = 0; i < len; i++) {
                dict.put(word.charAt(i), dict.getOrDefault(word.charAt(i), 0)+1);
            }
        }

        // mengubah hashMap menjadi List of key value agar bisa di loop
        List<Map.Entry<Character, Integer>> keyValueList = new ArrayList<>(dict.entrySet());

        // sorting list descending berdasarkan frekuensi, 2. urutan abjad, 3. huruf capital jika jumlah frekuensi sama
        int len = keyValueList.size();
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                // ambil isi dari List yang berisi Map key value
                Map.Entry<Character, Integer> kiri = keyValueList.get(j);
                Map.Entry<Character, Integer> kanan = keyValueList.get(j + 1);

                // Compare frekuensi lalu swap jika frekuensi lebih tinggi
                if (kanan.getValue() > kiri.getValue()) {
                    Collections.swap(keyValueList, j, j + 1);
                }
            }
        }
        // sorting list descending berdasarkan urutan abjad dan huruf capital jika jumlah frekuensi sama
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                Map.Entry<Character, Integer> kiri = keyValueList.get(j);
                Map.Entry<Character, Integer> kanan = keyValueList.get(j + 1);

                // hanya compare jika frekuensinya sama
                if (kiri.getValue().equals(kanan.getValue())) {
                    //compare character dan swap jika character lebih besar (urutan abjad atau kapital)
                    if (kiri.getKey().compareTo(kanan.getKey()) > 0 || Character.isLowerCase(kiri.getKey()) && Character.isUpperCase(kanan.getKey())){
                        Collections.swap(keyValueList, j, j + 1);
                    }
                }
            }
        }

        // satukan key menjadi string dari isi list yang sudah di sort
        StringBuilder response = new StringBuilder();
        for (var entry : keyValueList) {
            response.append(entry.getKey());
        }

        return response.toString();
    }
}
