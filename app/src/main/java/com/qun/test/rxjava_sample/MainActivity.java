package com.qun.test.rxjava_sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    String url = "https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=2502144641,437990411&fm=80&w=179&h=119&img.JPEG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        String[] args = new String[]{"Hello", " World"};
//        Observable.from(args).subscribe(new Action1<String>() {
//            @Override
//            public void call(String s) {
//                Log.e("weiqun12345", s);
//            }
//        });
//        final List<Student> studentList = new ArrayList<>();
////        Student[] students = (Student[]) studentList.toArray();
//        Student student1 = new Student();
//        List<Score> scores1 = new ArrayList<>();
//        scores1.add(new Score(1));
//        scores1.add(new Score(2));
//        student1.mScoreList = scores1;
//
//        Student student2 = new Student();
//        List<Score> scores2 = new ArrayList<>();
//        scores2.add(new Score(3));
//        scores2.add(new Score(4));
//        student2.mScoreList = scores2;
//
//        studentList.add(student1);
//        studentList.add(student2);
//
//
//        Observable.from(studentList).map(new Func1<Student, List<Score>>() {
//            @Override
//            public List<Score> call(Student student) {
//                return student.mScoreList;
//            }
//        }).subscribe(new Action1<List<Score>>() {
//            @Override
//            public void call(List<Score> scores) {
//                Log.e("weiqun12345", "scores=" + scores);
//            }
//        });
//
//
//        Observable.from(studentList
//        ).flatMap(new Func1<Student, Observable<Score>>() {
//            @Override
//            public Observable<Score> call(Student student) {
//                return Observable.from(student.mScoreList);
//            }
//        }).map(new Func1<Score, String>() {
//            @Override
//            public String call(Score score) {
//                return (score.score == 1) ? "yes" : "no";
//            }
//        }).subscribe(new Action1<String>() {
//            @Override
//            public void call(String str) {
//                Log.e("weiqun12345", "score=" + str);
//            }
//        });


//        final ImageView imageView = findViewById(R.id.iv);
//
//        Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                subscriber.onNext(url);
//            }
//        }).map(new Func1<String, Drawable>() {
//            @Override
//            public Drawable call(String s) {
//                Drawable drawable = null;
//                try {
//                    drawable = Drawable.createFromStream(new URL(s).openStream(), "src");
//                    return drawable;
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                return null;
//            }
//        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Drawable>() {
//            @Override
//            public void call(Drawable drawable) {
//                imageView.setBackground(drawable);
//            }
//        });


//        Observable.create(new Observable.OnSubscribe<Drawable>() {
//
//            @Override
//            public void call(Subscriber<? super Drawable> subscriber) {
////                Drawable drawable = getTheme().getDrawable(R.drawable.ic_launcher_background);
//                String url = "https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=2502144641,437990411&fm=80&w=179&h=119&img.JPEG";
//
//                Drawable drawable = null;
//                try {
//                    drawable = Drawable.createFromStream(new URL(url).openStream(), "src");
//                    subscriber.onNext(drawable);
//                    subscriber.onCompleted();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    subscriber.onError(e);
//                }
//            }
//        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Drawable>() {
//            @Override
//            public void call(Drawable drawable) {
//                imageView.setBackground(drawable);
//            }
//        });


//        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                subscriber.onNext("Hello");
//                subscriber.onNext("World!");
//                subscriber.onCompleted();
//            }
//        });
//
//        Observer<String> observer = new Observer<String>() {
//            @Override
//            public void onCompleted() {
//                Log.e("weiqun12345", "Observer onCompleted");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//                Log.e("weiqun12345", s);
//            }
//        };
//        Subscriber<String> stringSubscriber = new Subscriber<String>() {
//            @Override
//            public void onCompleted() {
//                Log.e("weiqun12345", "stringSubscriber onCompleted");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//                Log.e("weiqun12345", "stringSubscriber "+s);
//            }
//
//            @Override
//            public void onStart() {
//                super.onStart();
//                Log.e("weiqun12345", "stringSubscriber onStart()");
//            }
//
//            @Override
//            public void setProducer(Producer p) {
//                super.setProducer(p);
//            }
//        };
//        observable.subscribe(observer);
//        observable.subscribe(stringSubscriber);
//        observable.subscribe(new Action1<String>() {
//            @Override
//            public void call(String s) {
//                Log.e("weiqun12345","Action1 " + s);
//            }
//        });
    }
}
