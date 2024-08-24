package com.kalayciburak.commonpackage.util.constant;

public class Messages {
    public static class Entity {
        public static final String NOT_FOUND = "Herhangi bir kayıt bulunamadı.";
        public static final String FOUND = "kayıt bulundu.";
        public static final String SAVED = "Veri başarıyla kaydedildi.";
        public static final String UPDATED = "Veri başarıyla güncellendi.";
        public static final String DELETED = "Veri başarıyla silindi.";
    }

    public static class Entities {
        public static final String NOT_FOUND = "Herhangi bir kayıt bulunamadı.";
        public static final String FOUND = "Kayıtlar listelendi.";
        public static final String SAVED = "Veriler başarıyla kaydedildi.";
        public static final String UPDATED = "Veriler başarıyla güncellendi.";
        public static final String DELETED = "Veriler başarıyla silindi.";
    }

    public static class Error {
        public static final String UNEXPECTED = "Beklenmeyen bir hata oluştu. Lütfen daha sonra tekrar deneyiniz.";
        public static final String INTERNAL_SERVER_ERROR = "Sunucu hatası oluştu. Lütfen daha sonra tekrar deneyiniz.";
        public static final String INVALID_ARGUMENT = "Geçersiz argüman. Lütfen doğru bilgileri giriniz.";
        public static final String VALIDATION_ERROR = "Girilen bilgiler hatalı. Lütfen kontrol ediniz.";
        public static final String ENTITY_NOT_FOUND = "Herhangi bir kayıt bulunamadı.";
        public static final String ENTITY_EXISTS = "Kayıt zaten mevcut.";
    }

    public static class Inventory {
        public static class Product {
            public static final String NOT_FOUND = "Herhangi bir ürün bulunamadı.";
            public static final String FOUND = "Ürün bulundu.";
            public static final String LISTED = "Ürünler listelendi.";
            public static final String SAVED = "Ürün oluşturuldu.";
            public static final String UPDATED = "Ürün güncellendi.";
            public static final String DELETED = "Ürün silindi.";
        }

        public static class Category {
            public static final String NOT_FOUND = "Herhangi bir kategori bulunamadı.";
            public static final String FOUND = "Kategori bulundu.";
            public static final String LISTED = "Kategoriler listelendi.";
            public static final String SAVED = "Kategori oluşturuldu.";
            public static final String UPDATED = "Kategori güncellendi.";
            public static final String DELETED = "Kategori silindi.";
            public static final String LISTED_WITH_PRODUCTS = "Kategoriye ait ürünler listelendi.";
            public static final String NOT_FOUND_WITH_PRODUCTS = "Kategoriye ait ürün bulunamadı.";
            public static final String EXISTS = "Kategori zaten mevcut.";
        }
    }
}