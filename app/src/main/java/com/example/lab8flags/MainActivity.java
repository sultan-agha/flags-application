package com.example.lab8flags;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    AssetManager assets;// to get the images
    ImageView img;
    ImageButton nextbtn;
    TextView ques;
    TextView therightans;
    Button btn1, btn2, btn3;
    String[] imageList;// list la hot feha limages bas badon yet8ayaro(images)
    int right = 0;
    int wrong = 0;
    int counter = 1;
    int therightbtn;
    // single item array instance to store which element is selected by user initially
    // it should be set to zero meaning none of the element is selected by default
    final int[] checkedCItem = {0};
    // finish of choice dialog

    // start talking about region dialog

    // initialise the list items for the alert dialog


    // to be done (region and number of choices)
//    final String[] Regionitems = new String[]{"Europe", "Asia", "Africa","America"};
//    final boolean[] checkedRItems = new boolean[Regionitems.length];

    // copy the items from the main list to the selected item list for the preview
    // if the item is checked then only the item should be displayed for the user\


    // to be done (region and number of choices)
//    final List<String> selectedItems = Arrays.asList(Regionitems);
    // finished talking about region dialog


    String nameCorrectChoice;// the right
     AlertDialog dialog;
     //AlertDialog choices;
     //AlertDialog region;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assets = this.getAssets();
        // putting the ids
        img = findViewById(R.id.imageView5);
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        nextbtn = findViewById(R.id.imageButton2);
        ques = findViewById(R.id.textView);
        therightans = findViewById(R.id.textView3);
        nextbtn.setEnabled(false);
        fillQuestion();
        // what happen if i click the next button
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;
                therightans.setText("");
                btn1.setEnabled(true);
                btn2.setEnabled(true);
                btn3.setEnabled(true);
                nextbtn.setEnabled(false);
                btn1.setBackgroundColor(Color.MAGENTA);
                btn2.setBackgroundColor(Color.MAGENTA);
                btn3.setBackgroundColor(Color.MAGENTA);
                fillQuestion();
            }
        });
    }
    // to make the menu
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.mainmenu, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.choices:
//        choicesDialog();
//                return true;
//            case R.id.regions:
//                RegionDialog();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

    // to put an image
    public void fillQuestion() {
        ques.setText("Question " + counter + "/ 10");
        try {
            imageList = assets.list("png"); //images contains all the names of the files in the directory assets/png
            String theimg = imageList[(int) (Math.random() * imageList.length)];
            String nomoneImage = "png/" + theimg; //contains  the way of first file
            Drawable image = Drawable.createFromStream(assets.open(nomoneImage), nomoneImage);
            img.setImageDrawable(image);
            nameCorrectChoice = namegeneration(theimg);
            int choice = (int) (Math.random() * 3) + 1;
            switch (choice) {
                case 1:
                    therightbtn = 1;
                    btn1.setText(nameCorrectChoice + "");
                    btn2.setText(namegeneration(imageList[(int) (Math.random() * imageList.length)]));
                    btn3.setText(namegeneration(imageList[(int) (Math.random() * imageList.length)]));

                    break;
                case 2:
                    therightbtn = 2;
                    btn1.setText(namegeneration(imageList[(int) (Math.random() * imageList.length)]));
                    btn2.setText(nameCorrectChoice + "");
                    btn3.setText(namegeneration(imageList[(int) (Math.random() * imageList.length)]));
                    break;
                case 3:
                    therightbtn = 3;
                    btn1.setText(namegeneration(imageList[(int) (Math.random() * imageList.length)]));
                    btn2.setText(namegeneration(imageList[(int) (Math.random() * imageList.length)]));
                    btn3.setText(nameCorrectChoice + "");
                    break;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        //if btn 1 is clicked
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn1.setEnabled(false);
                btn2.setEnabled(false);
                btn3.setEnabled(false);
                nextbtn.setEnabled(true);
                if (therightbtn == 1) {
                    right++;
                    btn1.setBackgroundColor(Color.GREEN);
                    therightans.setText("CORRECT CHOICE");
                } else {
                    wrong++;
                    btn1.setBackgroundColor(Color.RED);
                    therightans.setText("INCORRECT the answer is"+nameCorrectChoice + "!!");
                }

                if (counter == 10) {
                    // 3mel popup dialog
                    nextbtn.setEnabled(false);
                    openDialog();

                }
            }
        });
        //if btn 2 is clicked
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn1.setEnabled(false);
                btn2.setEnabled(false);
                btn3.setEnabled(false);
                nextbtn.setEnabled(true);
                if (therightbtn == 2) {
                    right++;
                    btn2.setBackgroundColor(Color.GREEN);
                    therightans.setText("CORRECT CHOICE");
                } else {
                    wrong++;
                    btn2.setBackgroundColor(Color.RED);
                    therightans.setText("INCORRECT the answer is"+nameCorrectChoice + "!!");
                }
                if (counter == 10) {
                    nextbtn.setEnabled(false);
                    openDialog();
                }
            }
        });
        //if btn 3 is clicked
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn1.setEnabled(false);
                btn2.setEnabled(false);
                btn3.setEnabled(false);
                nextbtn.setEnabled(true);
                if (therightbtn == 3) {
                    right++;
                    btn3.setBackgroundColor(Color.GREEN);
                    therightans.setText("CORRECT CHOICE");
                } else {
                    btn3.setBackgroundColor(Color.RED);
                    therightans.setText("INCORRECT the answer is"+nameCorrectChoice + "!!");
                    wrong++;
                }
                if (counter == 10) {
                    nextbtn.setEnabled(false);
                    openDialog();
                }
            }
        });
    }

    public void checkAnswer(View v) {
        String s = ((Button) v).getText().toString();
    }


    public String namegeneration(String theimg) {
        int indexofdash = theimg.indexOf("-") + 1;
        int indexofpng = theimg.indexOf(".png");
        String name = theimg.substring(indexofdash, indexofpng);
        return name;
    }
    public void openDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(+wrong+" wrong clicks " +(right*10)+" % correct")
                .setNeutralButton("Reset Quiz", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        counter=1;
                        wrong=0;
                        right=0;
                        fillQuestion();
                        btn1.setEnabled(true);
                        btn2.setEnabled(true);
                        btn3.setEnabled(true);
                        ques.setText("Question " + counter + "/ 10");
                        therightans.setText(" ");
                        nextbtn.setEnabled(false);
                        btn1.setBackgroundColor(Color.MAGENTA);
                        btn2.setBackgroundColor(Color.MAGENTA);
                        btn3.setBackgroundColor(Color.MAGENTA);
                    }
                });
  dialog=builder.create();
  dialog.show();
  dialog.setCancelable(false);
    }
    //ftn dialog for choices
    public void choicesDialog() {
        AlertDialog.Builder choice = new AlertDialog.Builder(this);
        choice.setTitle("what are the number of choices u want ");
        final String[] choiceitems = new String[]{"3 choice", "6 choices", "9 choices"};
        choice.setSingleChoiceItems(choiceitems, checkedCItem[0], (choices, which) -> {
                    // update the selected item which is selected by the user so that it should be selected
                    // when user opens the dialog next time and pass the instance to setSingleChoiceItems method
                    checkedCItem[0] = which;

        // now also update the TextView which previews the selected item
//        tvSelectedItemPreview.setText("Selected Item is : " + listItems[which]);
            Toast.makeText(this,"Selected Item is : " + choiceitems[which] , Toast.LENGTH_SHORT).show();

        // when selected an item the dialog should be closed with the dismiss method
        choices.dismiss();
    });

    // set the negative button if the user is not interested to select or change already selected item
            choice.setNegativeButton("Cancel", (dialog, which) -> {

    });
        AlertDialog ADchoice=choice.create();
        ADchoice.show();
    }
// ftn dialog for region
// to be done (region and number of choices)
//    public void RegionDialog(){
//        AlertDialog.Builder region = new AlertDialog.Builder(this);
//        region.setTitle("Please select the region of the questions");
//       region.setMultiChoiceItems(Regionitems, checkedRItems,
//               (regions, which, isChecked) -> {
//                   checkedRItems[which] = isChecked;
//                   String currentItem = selectedItems.get(which);
//               });
//        // handle the positive button of the dialog
//        region.setPositiveButton("Done", (regions, which) -> {
//            for (int i = 0; i < checkedRItems.length; i++) {
//                if (checkedRItems[i]) {
////                    tvSelectedItemsPreview.setText(String.format("%s%s, ", tvSelectedItemsPreview.getText(), selectedItems.get(i)));
//                    Toast.makeText(this,"Selected Item is : " + selectedItems.get(i) , Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        // handle the negative button of the alert dialog
//        region.setNegativeButton("CANCEL", (regions, which) -> {});
//
//        // handle the neutral button of the dialog to clear the selected items boolean checkedItem
//        region.setNeutralButton("CLEAR ALL", (regions, which) -> {
//            Arrays.fill(checkedRItems, false);
//        });
//        dialog=region.create();
//        dialog.show();
//    }
}