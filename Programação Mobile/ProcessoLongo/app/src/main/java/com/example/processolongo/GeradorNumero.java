package com.example.processolongo;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import java.util.LinkedList;
import java.util.Random;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>

 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.

 */
public class GeradorNumero extends IntentService {


    public GeradorNumero() {
        super("GeradorNumero");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            int quantidade = intent.getIntExtra("quantidade", 10);
            LinkedList<Integer> numeros = new LinkedList<>();
            Random r = new Random(System.currentTimeMillis());
            for(int x =0; x<quantidade; x++){
                int novo = r.nextInt();
                numeros.add(novo);
                Log.d("Numero Gerado!", " "+ novo);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }

        }
    }
}