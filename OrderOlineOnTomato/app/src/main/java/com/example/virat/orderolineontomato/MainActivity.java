package com.example.virat.orderolineontomato;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    int quantity;
    String summary;
    boolean isordered=false;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.example_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.item1:
                Toast.makeText(this, "Item1 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item2:
                Toast.makeText(this, "Item2 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item3:
                Toast.makeText(this, "Item3 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.subItem1:
                Toast.makeText(this, "subItem1 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.subItem2:
                Toast.makeText(this, "subItem2 selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /* Called when the activity is about to become visible. */
    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Welcome SIR", Toast.LENGTH_SHORT).show();
    }

    /** Called when the activity has become visible. */
    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "app resumed", Toast.LENGTH_SHORT).show();
    }

    /** Called when another activity is taking focus. */
    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "app paused", Toast.LENGTH_SHORT).show();
    }

    /** Called when the activity is no longer visible. */
    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "app stopped", Toast.LENGTH_SHORT).show();
    }

    /** Called just before the activity is destroyed. */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "app destroyed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Do you want to exit?");
        builder.setCancelable(true);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.setPositiveButton("O.K", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                EditText ed=(EditText) findViewById(R.id.name_id);
                String name=ed.getText().toString();
                Toast.makeText(MainActivity.this, "BYE "+name+"!!.We miss you", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

    public void submitOrder(View view){
        isordered=true;
        EditText ed=(EditText) findViewById(R.id.name_id);
        String name=ed.getText().toString();
        int cost=quantity*20;
        TextView priceVar=(TextView) findViewById(R.id.orderSummary);
        summary="Hii "+name;
        Boolean hasWhipped,hasChocolate;
        CheckBox wb=(CheckBox)findViewById(R.id.whipped_cream_id);
        hasWhipped=wb.isChecked();
        CheckBox tb=(CheckBox)findViewById(R.id.chocolate_id);
        hasChocolate=tb.isChecked();
        if(hasWhipped && quantity!=0){//if quantity>0 then only adding topping cost
            cost+=5;
        }
        if(hasChocolate && quantity!=0){ //if quantity>0 then only adding topping cost
            cost+=5;
        }
        summary+="\nQuantity:"+quantity;
        summary+="\nHas WhippedCream?:";
        if(hasWhipped){
            summary+="Yes";
        }
        else
            summary+="No";
        summary+="\nHas ChocolateTopping?:";
        if(hasChocolate){
            summary+="Yes";
        }
        else
            summary+="No";
        summary+="\nTotal:$"+cost;
        summary+="\nThank you.\nWe hope you visit us again soon";
        priceVar.setText(summary);
    }
    public void increment(View view){
        quantity=quantity+1;
        TextView quantityVar=(TextView) findViewById(R.id.quantityVar);
        quantityVar.setText("" + quantity);
        if(isordered){
            TextView ordid=(TextView)findViewById(R.id.order_id);
            ordid.setText("update order summary");
            ordid.setBackgroundResource(R.color.colorAccent);
        }
    }
    public void decrement(View view){
        quantity=quantity-1;
        if(quantity==-1){
            quantity=0;
            return;
        }
        TextView quantityVar=(TextView) findViewById(R.id.quantityVar);
        quantityVar.setText("" + quantity);
        if(isordered){
            TextView ordid=(TextView)findViewById(R.id.order_id);
            ordid.setText("update order summary");
            ordid.setBackgroundResource(R.color.colorAccent);
        }
    }
    public void confirmOrder(View view) {
        if(quantity==0){
            //if quantity is 0 then no need to order
            Toast.makeText(this, "Quantity:0. Cant place order", Toast.LENGTH_SHORT).show();
        }
        else {
            submitOrder(null);//in case if increment button is pressed but instead of updating submitorder if confirm order is clicked
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:viratvamsi45@gmail.com")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_SUBJECT, "coffee order summary");
            if (intent.resolveActivity(getPackageManager()) != null) {
                intent.putExtra(Intent.EXTRA_TEXT, summary);
                Toast toast = Toast.makeText(this, "sending content to Gmail", Toast.LENGTH_SHORT);
                toast.show();
                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                startActivity(intent);
            }
        }
    }
}
