# Keep WorkManager & Coroutine support classes needed at runtime
-keep class androidx.work.** { *; }
-keep class kotlinx.coroutines.** { *; }

# Keep classes used by reflection if any (adjust if you use reflection)
#-keepclassmembers class * {
#    @com.google.gson.annotations.SerializedName <fields>;
#}
