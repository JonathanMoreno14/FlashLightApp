# FlashLightApp

FlashLight App for Android updated with Material Deisgn

![flashlightapp](https://user-images.githubusercontent.com/11635523/40404627-7253d4cc-5e1e-11e8-9c83-cc715848ddc9.png)


[![google-play-badge copy-v2](https://user-images.githubusercontent.com/11635523/40404678-b49c3c8e-5e1e-11e8-857a-f1152fb77e08.png)](https://play.google.com/store/apps/details?id=com.jm.jonathanmoreno.flashlightapp)

### Dependencies

```

implementation 'com.jakewharton:butterknife:8.8.1'
annotationProcessor 'com.jakewharton:butterknife-compiler:8.8.1'
implementation 'com.android.support:design:27.1.1'
implementation 'com.android.support:support-v4:27.1.1'

```

### Permissions

Add the permissions in your **app/manifests/AndroidManifest.xml** file

```xml

<uses-permission android:name="android.permission.CAMERA" />
<uses-permission android:name="android.permission.FLASHLIGHT" />
<uses-feature android:name="android.hardware.camera" />
<uses-feature android:name="android.hardware.camera.flash" />

```

### Styling

**app/res/values/styles.xml**

```xml
<!--Custom Theme with no ActionBar-->
<style name="AppThemefull" parent="Theme.AppCompat.Light.NoActionBar">
    <item name="colorPrimaryDark">@color/offLightColor</item>
    <item name = "android:windowActionBar">false</item>
    <item name = "android:windowNoTitle">true</item>
</style>

```

### Dimensions

Added dimensions **app/res/values/dimens.xml**

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <dimen name="buttonHeight">120dp</dimen>
    <dimen name="buttonWidth">120dp</dimen>
</resources>

```

### Colors

Added colors **app/res/values/colors.xml**

```xml

<color name="offLightColor">#212121</color>
<color name="onLightColor">#FAFAFA</color>

```
