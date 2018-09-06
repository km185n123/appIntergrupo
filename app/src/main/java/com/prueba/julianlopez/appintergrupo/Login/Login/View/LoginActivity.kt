package com.prueba.julianlopez.appintergrupo.Login.Login.View

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.support.v7.app.AppCompatActivity
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.view.View
import android.content.Intent
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.Toast
import com.mobsandgeeks.saripaar.ValidationError
import com.mobsandgeeks.saripaar.Validator
import com.mobsandgeeks.saripaar.annotation.Email
import com.mobsandgeeks.saripaar.annotation.NotEmpty
import com.mobsandgeeks.saripaar.annotation.Password
import com.mobsandgeeks.saripaar.annotation.Pattern
import com.prueba.julianlopez.appintergrupo.Login.Prospects.View.DashBoardActivity
import com.prueba.julianlopez.appintergrupo.Login.Login.LoginMVP
import com.prueba.julianlopez.appintergrupo.Login.Login.Presenter.Login
import com.prueba.julianlopez.appintergrupo.Login.Util.ConstantsMessages
import com.prueba.julianlopez.appintergrupo.Login.Util.DataCache
import com.prueba.julianlopez.appintergrupo.R
import kotlinx.android.synthetic.main.activity_login.*

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity(),  LoginMVP.View , Validator.ValidationListener{
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private var mAuthTask: UserLoginTask? = null

    val ACCESS_TOKEN = "accesToken"
    @NotEmpty(message = ConstantsMessages.OBLIGATORY_FIELD)
    @Pattern(regex = "[a-zA-Z0-9._-]+@[a-z]+.com",message = "Formato de correo no valido")
    @Email(message = ConstantsMessages.INVALID_EMAIL)
    lateinit var txtemail : AutoCompleteTextView

    @NotEmpty(message = ConstantsMessages.OBLIGATORY_FIELD)
    @Password
    private lateinit var txtPassword : EditText
    private val loginPresenter: LoginMVP.Presenter = Login(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setTitle("Login")
        // Set up the login form.


        txtemail = email
        txtPassword = password

        var validator =  Validator(this)
        validator.setValidationListener(this)
        validateSession()

        email_sign_in_button.setOnClickListener {  validator.validate() }
    }
    private fun validateSession() {

         emailStr = DataCache.readData(this,"EMAIL")
         passwordStr = DataCache.readData(this,"PASSWORD")
        REMENBER_CREDENTIALS = DataCache.readData(this,"REMENBER_CREDENTIALS").toBoolean()
        remenberSession.isChecked = REMENBER_CREDENTIALS

        if (!emailStr.isNullOrEmpty() && !passwordStr.isNullOrEmpty()){

            showProgress(true)
            mAuthTask = UserLoginTask(emailStr, passwordStr)
            mAuthTask!!.execute(null as Void?)
            email_login_form.visibility = View.INVISIBLE

        }else{
            email_login_form.visibility = View.VISIBLE
        }





    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */

    var emailStr = ""

    var passwordStr = ""
    private fun attemptLogin() {


        // Reset errors.
        email.error = null
        password.error = null

        // Store values at the time of the login attempt.
         emailStr = txtemail.text.toString()
         passwordStr = txtPassword.text.toString()


            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true)
            mAuthTask = UserLoginTask(emailStr, passwordStr)
            mAuthTask!!.execute(null as Void?)

    }
    /**
     * This method return a exception if it fails
     */
    override fun notificationError(error: String) {

        Toast.makeText(this,error,Toast.LENGTH_LONG).show()
        showProgress(false)
    }





    /**
     * This method allows you to go to the Dashboard
     */


    override fun notificationSuccess(token: String, network: Boolean) {


        if (network) {
            DataCache.writeData(this, ACCESS_TOKEN, token)

            if (remenberSession.isChecked) {
                DataCache.writeData(this, "EMAIL", emailStr)
                DataCache.writeData(this, "PASSWORD", passwordStr)
                DataCache.writeData(this, "REMENBER_CREDENTIALS", "true")

            }
        }


        if (!DataCache.readData(this,ACCESS_TOKEN).isNullOrEmpty()){

            val intent = Intent(this, DashBoardActivity::class.java)
            startActivity(intent)
            finish()
        }



    }

    override fun onValidationSucceeded() {

        attemptLogin()

    }

    override fun onValidationFailed(errors: MutableList<ValidationError>?) {
        if (errors != null) {
            for (error in errors) {
                val view = error.getView()
                val message = error.getCollatedErrorMessage(this)

                // Display error notification_layout ;)
                if (view is EditText) {
                    (view as EditText).error = message
                } else {
                    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }



    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private fun showProgress(show: Boolean) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()

            login_form.visibility = if (show) View.GONE else View.VISIBLE
            login_form.animate()
                    .setDuration(shortAnimTime)
                    .alpha((if (show) 0 else 1).toFloat())
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            login_form.visibility = if (show) View.GONE else View.VISIBLE
                        }
                    })

            login_progress.visibility = if (show) View.VISIBLE else View.GONE
            login_progress.animate()
                    .setDuration(shortAnimTime)
                    .alpha((if (show) 1 else 0).toFloat())
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            login_progress.visibility = if (show) View.VISIBLE else View.GONE
                        }
                    })
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            login_progress.visibility = if (show) View.VISIBLE else View.GONE
            login_form.visibility = if (show) View.GONE else View.VISIBLE
        }
    }



    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    inner class UserLoginTask internal constructor(private val mEmail: String, private val mPassword: String) : AsyncTask<Void, Void, Boolean>() {

        override fun doInBackground(vararg params: Void): Boolean? {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(1000)
                loginPresenter.login(mEmail,mPassword,this@LoginActivity)
                return true

            } catch (e: InterruptedException) {
                return false
            }


        }

        override fun onPostExecute(success: Boolean?) {


        }

        override fun onCancelled() {

        }
    }

    companion object {

        private var REMENBER_CREDENTIALS = true

    }
}
