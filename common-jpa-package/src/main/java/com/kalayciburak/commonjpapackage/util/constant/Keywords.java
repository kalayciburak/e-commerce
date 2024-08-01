package com.kalayciburak.commonjpapackage.util.constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Keywords {
    public static final List<String> creationKeywords = new ArrayList<>();

    private static final List<String> TR_CREATION_KEYWORDS = Arrays.asList(
            "kaydedildi", "kayıt", "kaydet",
            "oluşturuldu", "oluşturmak", "ekledi", "ekleme",
            "yeni ekleme", "yeni kayıt", "yeni oluşturma",
            "eklendi", "ekle", "ekleme işlemi"
    );

    private static final List<String> EN_CREATION_KEYWORDS = Arrays.asList(
            "saved", "save", "insert", "created", "create",
            "added", "add", "adding", "new add", "new record"
    );

    private static final List<String> FR_CREATION_KEYWORDS = Arrays.asList(
            "enregistré", "enregistrer", "insérer", "créé", "créer",
            "ajouté", "ajouter", "ajout", "nouvel ajout", "nouvel enregistrement"
    );

    static {
        creationKeywords.addAll(TR_CREATION_KEYWORDS);
        creationKeywords.addAll(EN_CREATION_KEYWORDS);
    }
}
