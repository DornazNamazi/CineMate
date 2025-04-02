package views.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cinemate.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import views.admin.AdminHomePage;
import views.customer.CustomerHomePage;

public class LoginPage extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseFirestore db;

    EditText edEmail, edPassword;
    Button btnLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.authentication_login_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edEmail = findViewById(R.id.edEmail);
        edPassword = findViewById(R.id.edPassword);
        btnLogIn = findViewById(R.id.btnLogIn);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        btnLogIn.setOnClickListener(v -> {
            Toast.makeText(this, "Login button clicked", Toast.LENGTH_SHORT).show();
            String email = edEmail.getText().toString();
            String password = edPassword.getText().toString();

            mAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(authResult -> {
                String userId = mAuth.getCurrentUser().getUid();
                Log.d("LoginPage", "Login success, userId = " + userId);
                db.collection("users").document(userId).get().addOnSuccessListener(document -> {
                    if (document.exists()) {
                        String role = document.getString("role");
                        Log.d("LoginPage", "Role = " + role);
                        if ("ADMIN".equalsIgnoreCase(role)) {
                            startActivity(new Intent(LoginPage.this, AdminHomePage.class));
                        } else if ("CUSTOMER".equalsIgnoreCase(role)) {
                            startActivity(new Intent(LoginPage.this, CustomerHomePage.class));
                        } else {
                            Toast.makeText(this, "Unknown role" + role, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }).addOnFailureListener(e ->
                    Toast.makeText(this, "Login failed: " + e.getMessage(), Toast.LENGTH_SHORT).show()
            );
        });


    }
}