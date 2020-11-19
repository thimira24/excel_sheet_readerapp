package me.thimira.excelapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;

import org.apache.log4j.chainsaw.Main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import me.thimira.excelapp.Adapter.Adapter;
import me.thimira.excelapp.TableAdapter.TableAdapter;

public class TableActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageView btnRefresh;
    TableAdapter adapter;
    AsyncHttpClient client;
    Workbook workbook;
    List<String> column1, column2, column3, column4;
    RelativeLayout popup_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        recyclerView = findViewById(R.id.list_of_table_view);
        popup_layout = findViewById(R.id.Relative_popup_sync);

        column1 = new ArrayList<>();
        column2 = new ArrayList<>();
        column3 = new ArrayList<>();
        column4 = new ArrayList<>();

        httpClient();
    }

    private void httpClient() {

        String url = "https://github.com/sheetasapp/excel-reader-android-app2/blob/master/story.xls?raw=true";
        client = new AsyncHttpClient();
        popup_layout.setVisibility(View.VISIBLE);
        client.get(url, new FileAsyncHttpResponseHandler(this) {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                popup_layout.setVisibility(View.GONE);
                startActivity(new Intent(TableActivity.this, SyncingFailedActivity.class));
                finish();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, File file) {
                popup_layout.setVisibility(View.GONE);
                Toast.makeText(TableActivity.this, "Syncing data..", Toast.LENGTH_SHORT).show();
                WorkbookSettings ws = new WorkbookSettings();
                ws.setGCDisabled(true);
                if (file != null) {
                    try {
                        workbook = workbook.getWorkbook(file);
                        Sheet sheet = workbook.getSheet(0);
                        for (int i = 0; i < sheet.getRows(); i++) {
                            Cell[] row = sheet.getRow(i);
                            column1.add(row[0].getContents());
                            column2.add(row[1].getContents());
                            column3.add(row[2].getContents());
                            column4.add(row[3].getContents());
                        }
                        showData();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (BiffException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void showData() {
        adapter = new TableAdapter(this, column1, column2, column3, column4);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.table_view_menu_items, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_help) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.Theme_AppCompat_Light_NoActionBar);
            View itemView = LayoutInflater.from(this).inflate(R.layout.layout_help, null);

            ImageView btn_close = (ImageView) itemView.findViewById(R.id.btn_close_help);

            builder.setView(itemView);
            final AlertDialog dialog = builder.create();
            dialog.show();

            btn_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            return true;
        }

        if (id == R.id.action_table_view) {
            startActivity(new Intent(TableActivity.this, MainActivity.class));
        }

        if (id == R.id.action_refresh) {
            Toast.makeText(TableActivity.this, "Refreshing...", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(TableActivity.this, TableActivity.class));
        }

        return super.onOptionsItemSelected(item);

    }
}