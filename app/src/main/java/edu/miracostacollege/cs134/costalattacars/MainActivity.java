package edu.miracostacollege.cs134.costalattacars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.text.NumberFormat;
import java.util.Locale;

import edu.miracostacollege.cs134.costalattacars.R;
import edu.miracostacollege.cs134.costalattacars.model.CarLoan;

public class MainActivity extends AppCompatActivity {


    private EditText carPriceEditText;
    private EditText downPaymentEditText;
    private RadioGroup loanTermRadioGroup;

    //reference model - carLoan
    private CarLoan loan;

    NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //link controller variables to view
        carPriceEditText = findViewById(R.id.carPriceEditText);
        downPaymentEditText = findViewById(R.id.downPaymentEditText);
        loanTermRadioGroup = findViewById(R.id.loanTermRadioGroup);

        //instantiate a new car loan
        loan = new CarLoan();

    }

    public void switchToLoanSummary(View v)
    {
        //1) extract data from two edit texts and radio group
        double carPrice = Double.parseDouble(carPriceEditText.getText().toString());
        double downPayment = Double.parseDouble(downPaymentEditText.getText().toString());

        int loanTerm = 3;
        // use a switch to determine radio button checked in radio group
        switch(loanTermRadioGroup.getCheckedRadioButtonId())
        {
            case R.id.threeYearsRadioButton:
                loanTerm = 3;

                break;

            case R.id.fourYearsRadioButton:
                loanTerm = 4;

                break;

            case R.id.fiveYearsRadioButton:
                loanTerm = 5;

                break;

        }

        // 2) update model CarLoan (object : loan)
        loan.setPrice(carPrice);
        loan.setDownPayment(downPayment);
        loan.setLoanTerm(loanTerm);

        //3) create new Intent to share the date between the activities
        //                                   Start activity, destination activity
        Intent loanSummarIntent = new Intent(this, LoanSummaryActivity.class);
        //share all the data!!!
        loanSummarIntent.putExtra("MonthlyPayment", currency.format(loan.monthlyPayment()));
        loanSummarIntent.putExtra("CarPrice",  currency.format(loan.getPrice()));

        //4 start activity
        startActivity(loanSummarIntent);





    }
}
