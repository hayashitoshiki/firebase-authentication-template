# firebase-authentication-template

FireBase認証のテンプレートコピペメモ(今後、自動テンプレート生成作成予定)  
　　　※google-services.jsonは上げてないため各自設定

##  各ファイルに飛ぶのが面倒くさい人用コピペメモ
Clean Architectureを想定として下記を実行すればOK  
➀下記domain,repository,をコピー  
➁build.gradleを設定  
➂google-services.jsonをインストール  
####  domain
```
class AuthenticationUseCaseImp(private val firebaseRepository: FirebaseRepository):AuthenticationUseCase {

    //ログイン状態チェック
    override fun firstCheck(onSuccess: () -> Unit, onError: () -> Unit){
        firebaseRepository.firstCheck(
            {
                onSuccess()
            },{
                onError()
            })
    }

    //ログイン
    override fun signIn(email: String, password: String,onSuccess: () -> Unit, onError: () -> Unit){
        firebaseRepository.signIn(email,password,
            {
                onSuccess()
            },{
                onError()
            })
    }
    //ログアウト
    override fun signOut(onSuccess: () -> Unit, onError: () -> Unit){
        firebaseRepository.signOut({
                onSuccess()
            },{
                onError()
            })
    }

    //アカウント作成
    override fun signUp(email:String, password:String,onSuccess: () -> Unit, onError: () -> Unit){
        firebaseRepository.signUp(email, password, {
                onSuccess()
            },{
                onError()
            })
    }

    //アカウント削除
    override fun delete(onSuccess: () -> Unit, onError: () -> Unit){
        firebaseRepository.delete({
                onSuccess()
            },{
                onError()
            })
    }

    //ユーザーのEmailを取得
    override fun getEmail():String{
        return firebaseRepository.getEmail()
    }

    //パスワード再設定
    override fun changePassword(onSuccess: () -> Unit, onError: () -> Unit){
        firebaseRepository.changePassword({
            onSuccess()
        },{
            onError()
        })
    }
}
```
#### repository
```
class FirebaseRepositoryImp: FirebaseRepository {

    companion object {
        private const val TAG = "FireBaseRepositoryImp"
    }

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()


    //ユーザーのEmailを取得
    override fun getEmail(): String {
        return auth.currentUser!!.email!!
    }

    //自動ログイン認証
    override fun firstCheck(onSuccess: () -> Unit, onError: () -> Unit) {
        if (auth.currentUser != null) {
            onSuccess()
        } else {
            onError()
        }
    }

    //ログイン機能
    override fun signIn(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "ログイン成功")
                    onSuccess()
                } else {
                    Log.d(TAG, "ログイン失敗", task.exception)
                    onError()
                }
            }

    }

    //ログアウト
    override fun signOut(onSuccess: () -> Unit, onError: () -> Unit) {
        auth.signOut()
        if (auth.currentUser == null) {
            Log.d(TAG, "ログアウト作成成功")
            onSuccess()
        } else {
            Log.d(TAG, "ログアウト失敗")
            onError()
        }
    }

    //アカウント作成
    override fun signUp(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "アカウント作成成功")
                    onSuccess()
                } else {
                    Log.w(TAG, "アカウント作成失敗", task.exception)
                    onError()
                }
            }
    }

    //ユーザー削除
    override fun delete(onSuccess: () -> Unit, onError: () -> Unit) {
        auth.currentUser!!.delete()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "ユーザーを削除しました")
                    onSuccess()
                } else {
                    Log.d(TAG, "予期せぬエラーが発生しました")
                    onError()
                }
            }
    }

    //メールアドレス変更
    override fun changePassword(onSuccess: () -> Unit, onError: () -> Unit){
        auth.sendPasswordResetEmail(getEmail())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "パスワード再設定")
                    onSuccess()
                } else {
                    Log.d(TAG, "予期せぬエラーが発生しました")
                    onError()
                }
            }
    }
}
```

