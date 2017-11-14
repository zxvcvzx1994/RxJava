package com.test1.vo.myapplication;

import android.database.Observable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.test1.vo.myapplication.Model.Name;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.Subject;
import rx.Subscriber;
import rx.functions.Func0;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Name name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = new Name("vinh");
        Integer [] a = {1,2,3};

        rx.Observable.create(new rx.Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onNext(1);
                subscriber.onNext(2);
                subscriber.onNext(3);
                subscriber.onCompleted();
            }

        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.i(TAG, "onNext: "+integer);
            }
        });
        rx.Observable.interval(2, TimeUnit.SECONDS).subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Long aLong) {
                if(aLong==5){

                    unsubscribe();
                    Log.i(TAG, "onNext: "+aLong);
                }
            }
        });
        rx.Observable<Name> observable = rx.Observable.defer(new Func0<rx.Observable<Name>>() {
            @Override
            public rx.Observable<Name> call() {
                return rx.Observable.just(name);
            }
        });

        name = new Name("COng");
        observable.subscribe(new Subscriber<Name>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Name name) {
                Log.i(TAG, "onNext: "+name.getName());
            }
        });
        rx.Observable.from(a).subscribe(new Subscriber<Integer>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer o) {
                Log.i(TAG, "onNext: "+o);
            }
        });

        rx.Observable.just(a).subscribe(new Subscriber<Integer[]>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer[] integers) {
                Log.i(TAG, "onNext: "+ Arrays.toString( integers));
            }
        });

    }


}
