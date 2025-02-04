package com.kalayciburak.commonpackage.core.constant;

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
        public static final String NO_SUCH_ELEMENT = "Herhangi bir eleman bulunamadı.";
        public static final String RESOURCE_NOT_FOUND = "Kaynak bulunamadı.";
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

        public static class Attribute {
            public static final String NOT_FOUND = "Herhangi bir özellik bulunamadı.";
            public static final String FOUND = "Özellik bulundu.";
            public static final String LISTED = "Özellikler listelendi.";
            public static final String SAVED = "Özellik oluşturuldu.";
            public static final String UPDATED = "Özellik güncellendi.";
            public static final String DELETED = "Özellik silindi.";
        }

        public static class Image {
            public static final String NOT_FOUND = "Herhangi bir resim bulunamadı.";
            public static final String FOUND = "Resim bulundu.";
            public static final String LISTED = "Resimler listelendi.";
            public static final String SAVED = "Resim oluşturuldu.";
            public static final String UPDATED = "Resim güncellendi.";
            public static final String DELETED = "Resim silindi.";
        }

        public static class Review {
            public static final String NOT_FOUND = "Herhangi bir yorum bulunamadı.";
            public static final String FOUND = "Yorum bulundu.";
            public static final String LISTED = "Yorumlar listelendi.";
            public static final String SAVED = "Yorum oluşturuldu.";
            public static final String UPDATED = "Yorum güncellendi.";
            public static final String DELETED = "Yorum silindi.";
        }

        public static class Category {
            public static final String NOT_FOUND = "Herhangi bir kategori bulunamadı.";
            public static final String FOUND = "Kategori bulundu.";
            public static final String LISTED = "Kategoriler listelendi.";
            public static final String SAVED = "Kategori oluşturuldu.";
            public static final String UPDATED = "Kategori güncellendi.";
            public static final String DELETED = "Kategori silindi.";
            public static final String SUBCATEGORY_INFO = "Kategori ve alt kategori bilgileri getirildi.";
            public static final String PARENT_CATEGORIES_INFO = "Ana kategoriler ve alt kategori bilgileri getirildi.";
            public static final String EXISTS = "Kategori zaten mevcut.";
        }
    }
}