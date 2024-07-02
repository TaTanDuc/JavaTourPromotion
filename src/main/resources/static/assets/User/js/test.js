document.getElementById('registerForm').addEventListener('submit', async function(event) {
    event.preventDefault(); // Prevent the default form submission

    const username = document.getElementById('username').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    const data = {
        username: username,
        email: email,
        password: password
    }

    try {
        const response = await fetch('http://localhost:8080/api/v1/security/register', {
            method: 'POST',
            body: data
        });

        if (!response.ok) {
            throw new Error('Network response was not ok');
        }

        const result = await response.json();
        console.log('Success:', result);
        // Handle success, e.g., show a success message or redirect
    } catch (error) {
        console.error('Error:', error);
        // Handle error, e.g., show an error message
    }
});