## sqlcipher
-keep,includedescriptorclasses class net.sqlcipher.** { *; }
-keep,includedescriptorclasses interface net.sqlcipher.** { *; }

## karena FavoriteFragment pakai by viewModels dgn factory, jadi perlu diberi exception
#noinspection ShrinkerUnresolvedReference
-keep class com.rhezarijaya.favorite.ui.favorite.FavoriteViewModelFactory {public *;}