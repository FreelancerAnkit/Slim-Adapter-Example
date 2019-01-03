package databinding.rapidera.slimadapterexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import net.idik.lib.slimadapter.SlimAdapter;
import net.idik.lib.slimadapter.SlimInjector;
import net.idik.lib.slimadapter.viewinjector.IViewInjector;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recycler_list;
    SlimAdapter slimAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler_list = findViewById(R.id.recycler_list);
        recycler_list.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        slimAdapter = SlimAdapter.create()
                .register(R.layout.item_user, new SlimInjector<User>() {
                    @Override
                    public void onInject(User data, IViewInjector injector) {
                        injector.text(R.id.textView2,data.title);
                    }
                })
                .registerDefault(R.layout.item_string, new SlimInjector() {
                    @Override
                    public void onInject(Object data, IViewInjector injector) {

                        injector.text(R.id.textView,data.toString());
                    }
                })
                .attachTo(recycler_list);

        List<Object> data = new ArrayList<>();

        {
            data.add("hello");
            data.add("hello");
            data.add("hello");
            data.add("hello");
            data.add("hello");
            data.add(new User("Ankit"));
        }

        slimAdapter.updateData(data);
    }
}
