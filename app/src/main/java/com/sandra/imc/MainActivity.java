package com.sandra.imc;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    /*------------------------------------------------------------------------------------------*/
    //Attributs
    /*------------------------------------------------------------------------------------------*/
    /**
     * Declaration des pointeurs
     */

    private Button btnCalcul,btnRAZ;
    private RadioButton btnMetre,btnCentimetre;
    private TextView txtPoids,txtTaille, txtResult;
    private CheckBox btnCheck;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initButton();
        txtResult = findViewById(R.id.txtResult);
        txtPoids = findViewById(R.id.txtPoids);
        txtTaille = findViewById(R.id.txtTaille);
        listenerButton();
    }
    /*------------------------------------------------------------------------------------------*/
        //Evenement
    /*------------------------------------------------------------------------------------------*/
    /**
     * rattacher l'ecouteur sur tous les boutons
     */
    @Override
    public void onClick(View view) {
        if (view.equals(btnCentimetre)) {
            //Toast.makeText(this, "Btn Centimetre", Toast.LENGTH_SHORT).show();
            btnCentimetre.setChecked(true);
            btnMetre.setChecked(false);

        }
        if (view.equals(btnMetre)) {
            //Toast.makeText(this, "Btn Metre", Toast.LENGTH_SHORT).show();
            btnCentimetre.setChecked(false);
            btnMetre.setChecked(true);
        }

        if (view.equals(btnCalcul)){
            caculateIMC(view);
        }
        if(view.equals(btnRAZ)){
            btnCentimetre.setChecked(false);
            btnMetre.setChecked(true);
            initButton();
            txtPoids.setText("");
            txtTaille.setText("");
            txtResult.setText("");}

    }

    /*------------------------------------------------------------------------------------------*/
    //Méthodes
    /*------------------------------------------------------------------------------------------*/
    /**
     * factorisation des méthodes
     */
    //pour les boutons

    public void initButton(){
        btnCalcul = findViewById(R.id.btnCalcul);
        btnCentimetre = findViewById(R.id.btnCentimetre);
        btnCheck = findViewById(R.id.btnCheck);
        btnMetre =  findViewById(R.id.btnMetre);
        btnRAZ = findViewById(R.id.btnRAZ);



    }

    public void listenerButton(){
        /**
         * Ajout des écouteurs sur les boutons
         */
        btnCalcul.setOnClickListener(this);
        btnCentimetre.setOnClickListener(this);
        btnCheck.setOnClickListener(this);
        btnMetre.setOnClickListener(this);
        btnRAZ.setOnClickListener(this);
    }

    /**
     *cette méthode est la formule pour calculer l'indice de masse corporel
     */
    public void caculateIMC(View view){
        String taille = txtTaille.getText().toString();
        String poids =  txtPoids.getText().toString();

        if (taille != null && !"".equals(taille)
         && poids != null && !"".equals(poids)){
            float tailleValue =  Float.parseFloat(taille) / 100;
            float poidsvalue =  Float.parseFloat(poids);

            float IMC = tailleValue / (poidsvalue * tailleValue);

            displayIMC(IMC);
        }
    }
    private void displayIMC(float IMC){
        String fourchette =  "";

        if (IMC < 18.5){
            fourchette =  getString(R.string.insuffisant);
        }
        else if(IMC == 18.5 && IMC > 24.9 ){
            fourchette = getString(R.string.normal);
        }
        else if(IMC == 25 && IMC > 29.9){
            fourchette = getString(R.string.embonpoint);
        }
        else if(IMC == 30 && IMC > 34.9){
            fourchette =  getString(R.string.obesite_class_i);
        }
        else if(IMC == 35 && IMC >39.9){
            fourchette =  getString(R.string.obesite_class_ii);
        }
        else {
            fourchette = getString(R.string.obesite_class_iii);
        }
         fourchette =  IMC +"\n" + fourchette;

        txtResult.setText(fourchette);
    }
}
