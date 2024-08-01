package com.kalayciburak.commonjpapackage.util.constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Keywords {
    public static final List<String> createdKeywords = new ArrayList<>();

    private static final List<String> tr_keywords = Arrays.asList(
            "kaydedildi", "kayıt", "kaydet",
            "oluşturuldu", "oluşturmak", "ekledi", "ekleme",
            "yeni ekleme", "yeni kayıt", "yeni oluşturma",
            "eklendi", "ekle", "ekleme işlemi"
    );

    private static final List<String> en_keywords = Arrays.asList(
            "saved", "save", "insert", "created", "create",
            "added", "add", "adding", "new add", "new record"
    );

    private static final List<String> fr_keywords = Arrays.asList(
            "enregistré", "enregistrer", "insérer", "créé", "créer",
            "ajouté", "ajouter", "ajout", "nouvel ajout", "nouvel enregistrement"
    );

    static {
        createdKeywords.addAll(tr_keywords);
        createdKeywords.addAll(en_keywords);
    }
}
