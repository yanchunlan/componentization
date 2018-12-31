# componentization
组件化项目开发实战
####概念
将APK拆分为多个模块（组件）进行开发，开发阶段组件可单独测试，通用组件可进行依赖复用，待开发完毕阶段，将所有模块（组件）进行合并，合并为真正的APK.
组件化——模块组件与通用组件，可分为业务组件与Ui组件等等
比如下拉刷新可作为多个项目使用的Ui组件，如登录模块有多条业务线采用相同逻辑，可作为业务组件存在，多个团队使用

#### 好处
- 业务组件可以单独分配并行开发
- 单个组件业务可以由开发者自行决定采取MVC/MVP/MVVM架构而不影响整体大局
- 新同学接手项目分配任务可单独分配某一个模块任务，不必关心整个项目
- 若公司有多个团队，优秀代码组件可快速移植复用
- 测试可单独测试某个模块

#### 效果图
![效果图.gif](https://upload-images.jianshu.io/upload_images/9387746-04c9a3837c3f621d.gif?imageMogr2/auto-orient/strip)

#### 项目结构
![项目结构.png](https://upload-images.jianshu.io/upload_images/9387746-cefcf9579f36a876.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

#### 版本控制
app目录下新建一个config,gradle文件，用于版本统一管理和控制
```
ext {
    isApplication = false
    android = [
            compileSdkVersion: 27,
            applicationId    : "com.peakmain.testproject",
            minSdkVersion    : 19,
            targetSdkVersion : 27,
            versionCode      : 1,
            versionName      : "1.0"
    ]
    dependencies = [
            support_v7       : 'com.android.support:appcompat-v7:27.1.1',
            design           : 'com.android.support:design:27.1.1',
            cardview           : 'com.android.support:cardview-v7:27.1.1',
            constraint_layout: 'com.android.support.constraint:constraint-layout:1.1.3',
            ultra_refresh: 'in.srain.cube:ultra-ptr:1.0.11',
            circleimageview: 'de.hdodenhof:circleimageview:2.2.0',
            banners: 'com.allure0:LMBanners:1.0.8',
            recycleviewAdapter: 'com.allure0:LMRecycleViewAdapter:1.0.1',
            glide:  'com.github.bumptech.glide:glide:3.7.0',
            activityrouter:  'com.github.mzule.activityrouter:activityrouter:1.2.2',
            activityroutercompiler:  'com.github.mzule.activityrouter:compiler:1.1.7',
            eventbus: 'org.greenrobot:eventbus:3.0.0'

    ]
}
```
在项目的根目录build.gradle下的第一行添加
```
apply from: "config.gradle"
```
如自己新建的model名字为modela的build.gradle修改后的代码
```
if (rootProject.ext.isApplication) {
    apply plugin: 'com.android.application'
} else {
    apply plugin: 'com.android.library'
}
android {
    compileSdkVersion rootProject.ext.android.compileSdkVersion
    defaultConfig {
        if (rootProject.ext.isApplication) {
            applicationId "com.peakmain.modulea"
        } else {
        }
        minSdkVersion rootProject.ext.android.minSdkVersion
        targetSdkVersion rootProject.ext.android.targetSdkVersion
        versionCode rootProject.ext.android.versionCode
        versionName rootProject.ext.android.versionName
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
        resourcePrefix "modulea_"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
    sourceSets {
        main {
          .//需要在目录main目录下新建两个目录分别为debug和release
            if (rootProject.ext.isApplication) {
                manifest.srcFile 'src/main/debug/AndroidManifest.xml'
            } else {
                manifest.srcFile 'src/main/release/AndroidManifest.xml'
                java {
                    //model的时候移除无用的包
                    exclude 'debug/**'
                }
            }
        }
    }
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation rootProject.ext.dependencies.support_v7
    implementation rootProject.ext.dependencies.constraint_layout
    implementation rootProject.ext.dependencies.baselibrary
    implementation 'com.android.support:design:27.1.1'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.2'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.2'

}
```

**重点说明几个**  
- 1.动态切换application与lib模式，我们看上面的代码我们在config.gradle中用isApplication来管理，在module中使用一下来切换,false即为lib模式，true则为application
```
if (rootProject.ext.isApplication) {
    apply plugin: 'com.android.application'
} else {
    apply plugin: 'com.android.library'
}
```
- 2.组件与组件之间的资源命名冲突，解决方法很简单,添加以下代码就可以了，他代表在项目的名字前添加前缀名
```
 resourcePrefix "modulea_"
```
- 3.组件与组件之间跳转

**第一种方式:Intent与intent之间跳转**
>注意一点，这里假设有modulea和moduleb两个module，我们只需要在在app项目中，添加modulea和moduleb即可，并不需要让modela和moduleb之间相互依赖，也可以实现，modulea跳转到moduleb

```
 //第一种方式intent跳转
    private void goClassForName(String packageName) {
        try {
            Class aClass = Class.forName(packageName);
            Intent intent = new Intent(activity, aClass);
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
goClassForName("com.peakmain.loginmodule.LoginActivity")`
```
**第二种方式:ActivityRouter跳转**
github:https://github.com/mzule/ActivityRouter
```
 // Routers.open(activity, "loginmodule://login");//单纯跳转
//带参数的跳转并赋值
 Routers.open(activity, "loginmodule://login?username=peakmain&userpassword=123456");
```
**第三种方式跳转eventbus**
github:https://github.com/greenrobot/EventBus

>**最后GitHub项目源码地址：https://github.com/Peakmain/componentization**
