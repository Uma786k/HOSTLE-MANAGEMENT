package com.androidstudio.final1;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Leave_Application extends AppCompatActivity {

    Spinner branch_spinner;
    Spinner year_spinner;

    EditText dateFrom;
    EditText dateTo;

    private String studentName, studentReason, Date_from, Date_to;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_application);

        EditText name = (EditText) findViewById(R.id.apply_et_Name);
        EditText reason = (EditText) findViewById(R.id.apply_et_reason);
        Spinner string_branch = (Spinner) findViewById(R.id.apply_et_branch);
        Spinner string_year = (Spinner) findViewById(R.id.apply_et_year);


//        new button
        Button btn_click_new = (Button) findViewById(R.id.btn_new);
        btn_click_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Leave_Application.this, Table1.class);
                startActivity(intent);
            }
        });

                //APPLY BUTTON
//        db pe update hona chahiye
        Button btn_click_apply = (Button) findViewById(R.id.btn_apply);
        btn_click_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_name = name.getText().toString();
                String str_reason = reason.getText().toString();
                String str_branch = branch_spinner.getSelectedItem().toString();
                String str_year = year_spinner.getSelectedItem().toString();

                // validating the text fields if empty or not.
                if (TextUtils.isEmpty(str_name)) {
                    name.setError("Please enter Name");
                } else if (TextUtils.isEmpty(str_reason)) {
                    reason.setError("Please enter Reason");
//                } else if (TextUtils.isEmpty(str_branch)) {
//                    str_branch.setError("Please enter branch");
//                } else if (TextUtils.isEmpty(str_year)) {
//                    str_year.setError("Please enter course year");
                } else {
                    // calling method to add data to Firebase Firestore.
                    addDataToDatabase(studentName, studentReason, Date_from, Date_to);
                }

            }
        });

//        Date and Time dialogue
                dateFrom = findViewById(R.id.dateFrom);
                dateTo = findViewById(R.id.dateTo);

                dateFrom.setInputType(InputType.TYPE_NULL);
                dateTo.setInputType(InputType.TYPE_NULL);

                dateFrom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showDateAndTimeDialogFrom(dateFrom);
                    }
                });

                dateTo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showDateTimeDialogTo(dateTo);
                    }
                });


//For branch spinner
// Create an ArrayAdapter using the string array and a default spinner layout
// Specify the layout to use when the list of choices appears
// Apply the adapter to the spinner
                branch_spinner = (Spinner) findViewById(R.id.apply_et_branch);
                ArrayAdapter<CharSequence> adapterForBranchSpinner = ArrayAdapter.createFromResource(this,
                        R.array.branch, android.R.layout.simple_spinner_item);
                adapterForBranchSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                branch_spinner.setAdapter(adapterForBranchSpinner);


//For year spinner
                year_spinner = (Spinner) findViewById(R.id.apply_et_year);
                ArrayAdapter<CharSequence> adapterForYearSpinner = ArrayAdapter.createFromResource(this,
                        R.array.year, android.R.layout.simple_spinner_item);
                adapterForYearSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                year_spinner.setAdapter(adapterForYearSpinner);

    }




    //Add to database

    private void addDataToDatabase(String name, String reason, String Date_from, String Date_to) {

        // url to post our data
        String url = "http://192.168.0.164//MAD//process.php";

        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(Leave_Application.this);

        // on below line we are calling a string
        // request method to post the data to our API
        // in this we are calling a post method.
        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("TAG", "RESPONSE IS " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    // on below line we are displaying a success toast message.
                    Toast.makeText(Leave_Application.this, jsonObject.getString("Success"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // and setting data to edit text as empty
//                name.setText("");
//                reason.setText("");
//                courseDurationEdt.setText("");
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(Leave_Application.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public String getBodyContentType() {
                // as we are passing data in the form of url encoded
                // so we are passing the content type below
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() {

                // below line we are creating a map for storing
                // our values in key and value pair.
                Map<String, String> params = new HashMap<String, String>();

                // on below line we are passing our
                // key and value pair to our parameters.
                params.put("studentName", studentName);
                params.put("studentReason", studentReason);
                params.put("Date_from", Date_from);
                params.put("Date_to", Date_to);

                // at last we are returning our params.
                return params;
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);
    }





//Date and time pickers below


            private void showDateTimeDialogTo(final EditText dateTo) {
                final Calendar calendar = Calendar.getInstance();
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR,year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hourDay, int minute) {
                                calendar.set(Calendar.HOUR_OF_DAY, hourDay);
                                calendar.set(Calendar.MINUTE, minute);

                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm");

                                dateTo.setText(simpleDateFormat.format(calendar.getTime()));

                            }
                        };

                        new TimePickerDialog(Leave_Application.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
                    }
                };
                new DatePickerDialog(Leave_Application.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }

            private void showDateAndTimeDialogFrom(final EditText dateFrom) {
                final Calendar calendar = Calendar.getInstance();
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR,year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hourDay, int minute) {
                                calendar.set(Calendar.HOUR_OF_DAY, hourDay);
                                calendar.set(Calendar.MINUTE, minute);

                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm");

                                dateFrom.setText(simpleDateFormat.format(calendar.getTime()));

                            }
                        };

                        new TimePickerDialog(Leave_Application.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
                    }
                };
                new DatePickerDialog(Leave_Application.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }






}


