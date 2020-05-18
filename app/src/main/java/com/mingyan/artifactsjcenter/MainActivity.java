package com.mingyan.artifactsjcenter;
/**20200515AndroidStudio3.5.3，Try publish jcenter。
 * Upload Java Artifacts to Maven Central Repository and jcenter。
 * GUIDE : https://inthecheesefactory.com/blog/how-to-upload-library-to-jcenter-maven-central-as-dependency/en
 * 第一部分 - 1_在bintray.com上創建一個repositiory再創package，package(小寫單詞用-相連)名稱不是Library的名稱不用相同，
 * Licenses : Apache2.0，Website : adnroid project上傳在github的網址無 點git，
 * Issues tracker :  adnroid project上傳在github的網址點Issues進去的網址，
 * Version control : 同Website網址。
 * 如果您沒有計劃將庫上載到Maven Central，則可以跳過第2部分和第3部分。
 * 第二部分 - 1_為Maven Central創建issues.sonatype.org帳戶，再按create button創一個Issues。
 * 填寫以下信息：
 * Project :  Community Support - Open Source Project Repository Hosting
 * Issue Type : New Project
 * Summary : for example, The Cheese Library
 * 再來去Bintray的下拉有登出選項處選Edit Profile > Accounts > 選google登入,然後Sonatype OSS User填sonatype帳號名，Sonatype OSS password填sonatype密碼。
 * 第3部分：在Bintray中啟用自動簽名
 * 先安裝Git Bash，通過命令行生成密鑰 gpg --gen-key，查看創建的密鑰的信息gpg --list-keys，
 * 將公共密鑰上載到密鑰服務器以使其有用 gpg --keyserver hkp://pool.sks-keyservers.net --send-keys  PUBLIC_KEY_ID(替代為上一指令產生的pub 後面的長串)
 * 將公用密鑰導出為ASCII裝甲格式  gpg -a --export xxxx@email.com > public_key_sender.asc
 * 將專用密鑰導出為ASCII裝甲格式  gpg -a --export-secret-key xxxx@email.com > private_key_sender.asc
 * 導出ASCII裝甲格式文件用Git Bash指令gpg --version到顯示Home : 的位置找就有了。
 * 去Bintray的下拉有登出選項處選Edit Profile > GPG Signing的Public Key裡面用public_key_sender.asc文件用筆記本打開全複製填上，
 * Private Key裡面用private_key_sender.asc文件用筆記本打開全複製填上，
 * 再來是啟用自動簽名，轉到Bintray的主頁點創建了的xxxpackage(maven)進去再點Owned by  xxx右邊有 Edit 按鈕點下去，
 * 將GPG sign uploaded files automatically 框啟用/勾選，單擊更新  以保存進度。從現在開始，上傳到我們的Maven資源庫的每個庫都將被自動簽名，只需單擊一下即可轉發到Maven Central。
 * 第4部分：準備一個Android Studio項目
 * File > New > New Moudle > Android Library。
 * build.gradle(Project) 的 dependencies {
 *      classpath 'com.jfrog.bintray.gradle:gradle-bintray-plugin:1.4'
 *      classpath 'com.github.dcendents:android-maven-gradle-plugin:2.1'
 * }
 * local.properties(SDK Location)增加
 * bintray.user=BINTRAY帳戶名
 * bintray.apikey=Bintray的下拉有登出選項處選Edit Profile > API Key > 輸入Bintray密碼就有了
 * bintray.gpg.password=這是第3部分生成gpg key時候填入的密碼 ( 或翻譯是填入的密碼短語 )
 * 再來是build.gradle(Module:該xxxLibrary)，增加ext { xxxx... }，
 * apply from: 'https://raw.githubusercontent.com/nuuneoi/JCenter/master/installv1.gradle'
 * apply from: 'https://raw.githubusercontent.com/nuuneoi/JCenter/master/bintrayv1.gradle'
 * 這兩個apply from增加在{ }之外各自單獨一行，要一模一樣不用改nuuneoi。
 * 第5部分：將庫上傳到您的Bintray空間
 * 轉到Android Studio上的Terminal，檢查代碼的正確性並構建庫文件，輸入命令gradlew install，沒錯將顯示BUILD SUCCESSFUL，
 * 將生成的文件上傳到bintray，輸入gradlew bintrayUpload 沒錯將顯示 SUCCESSFUL。*/
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.mingyan.toasttalk.ToastTalk;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickShort(View view){
        ToastTalk.vShort(view.getContext(), "ShorShort HelloWorld!!");
    }

    public void clickLong(View view){
        ToastTalk.vLong(this, "LongLong HelloWorld!!");
    }

}
