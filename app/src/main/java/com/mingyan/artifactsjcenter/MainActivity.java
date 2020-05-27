package com.mingyan.artifactsjcenter;
/**20200515AndroidStudio3.5.3，Try publish jcenter。
 * Upload Java Artifacts to Maven Central Repository and jcenter。
 * GUIDE : https://inthecheesefactory.com/blog/how-to-upload-library-to-jcenter-maven-central-as-dependency/en
 * 第一部分 - 1_在bintray.com上創建一個repositiory再創package，package(小寫單詞用-相連)名稱不是Library的名稱不用相同，
 * Licenses : Apache2.0，Website : adnroid project上傳在github的網址無 點git，
 * Issues tracker :  adnroid project上傳在github的網址點Issues進去的網址，
 * Version control : 同Website網址。
 * 如果您沒有計劃將庫上載到Maven Central，則可以跳過第2部分和第3部分。
 * 第二部分 - 1_為Maven Central創建issues.sonatype.org帳戶，再按create button創一個Issues，
 * 填寫以下信息：(在剛創好帳號時點這個進去create issues裡面的欄位都未全出現，20200518_20:xx再次點create嘗試創建 Issue以下欄位材都有出現)
 * Project :  Community Support - Open Source Project Repository Hosting
 * Issue Type : New Project
 * Summary : for example, The Cheese Library
 * Group Id : 放置根GROUP_ID[不可以自記瞎掰要擁有該domain]，例如com.inthecheeselibrary。獲得批准後，每個庫都以com.inthecheeselibrary開頭，例如com.inthecheeselibrary.somelib。
 * Project URL : 放置您計劃分發的任何庫的URL，例如https://github.com/nuuneoi/FBLikeAndroid
 * SCM URL : 源代碼管理的URL，例如https://github.com/nuuneoi/FBLikeAndroid.git
 * 其餘部分保持不變，然後單擊“ 創建”。就這樣。現在，這是最困難的部分……耐心等待……平均大約需要1週或更多時間。之後，您將被授予將您的庫分發到Maven Central的權限。
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
 * 將生成的文件上傳到bintray，輸入gradlew bintrayUpload 沒錯將顯示 SUCCESSFUL。
 * 到此Library尚未發到Maven Central，也沒在jcenter。
 *
 * 第6部分：將Bintray用戶存儲庫同步到jcenter
 * 點進Bintray的xxxPackage(Maven) > 再點進該Library > Linked to (阿拉伯數字x)的右邊 > 點Add to JCenter(在右上帳號icon 帳號xxx的下面有Actions下拉才有Add to JCenter) > 畫面轉跳後直接點Send不用特別作什麼。
 * 至此等待2-3個小時(5+[2-3]=7-8)，讓Bintray小組批准我們的請求。同步請求獲得批准後，您將收到一封電子郵件，通知您進行更改。現在讓我們檢查Web界面，您將在剛剛的Linked To  部分中看到一些更改，
 * 實際等了1.5小時左右獲得批准，註冊Bintray的mail和Bintray都會收到通知，Linked To(數字有變) 右邊不再是Add to JCenter變成Stage snapshots on oss.jfrog.org。
 * 到目前為止因為新創sonatype.org帳號導致在sonatype上創建issues時的欄位未出現所以很多沒填，
 * 可能相當於第2部分不算數，在此toasttalk有成功publish於JCenter，
 * 但是後來只做第5部分這一步_用AndroidStudio的Terminal直接push到Github的0.1.1版本(有改GroupId)的是不能成功運作的
 * ，可能只是Add到Bintray，需要用 第6部分Add to JCenter，但是點進該版本已找不到Add to JCenter選項，
 * [ https://stackoverflow.com/questions/41084693/how-to-update-library-for-new-version-in-bintray   20200520/11:45再次Try to add version很快就依賴成功 ]，
 * update version on JCenter就是改build.gradle(Module:toasttalk)的ext{ libraryVersion = 'x.x.x' ，改GroupId不能生效本次沒改, version '0.1.3' 改GroupId真的沒用}
 * ，再點進Bintray的xxxPackage(Maven)或 xxxRepositiory(Maven) > 點選Package Name這邊是toasttalk > 點Add Version > Name填 version , 選日期 , 填描述 > 按Create Version
 * ，用Android Studio的Terminal輸入gradlew bintrayUpload顯示BUILD SUCCESSFUL很快就可以依賴成功。
 *
 * 第7部分：將庫轉發到Maven Central
 * 首先 1_Bintray軟件包必須已經鏈接到jcenter。
 *          2_Maven Central上的存儲庫已被批准打開(就是第2部分的sonatype.org創見issues)
 * 到Bintray的主頁點創建了的xxxpackage(maven)進去再選點該xxx(Package Name)若是Maven Central上的存儲庫已被批准，只需單擊 軟件包詳細信息頁面上的Maven Central鏈接即可。
 *
 * How to Publish a Java Library to Maven Central
 * https://www.youtube.com/watch?v=bxP9IuJbcDQ&t=322s   [  12:46 此方法沒成功]
 * pom.xml內容是從https://github.com/simpligility/ossrh-demo/blob/master/pom.xml參考的。
 * */
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
