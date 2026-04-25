export const authStore = $state({
    users: [],
    currentUser: null
});

export function registerUser(username, password) {
    const exists = authStore.users.find(u => u.username === username);
    if (exists) {
        return { success: false, message: 'Username already exists' };
    }

    authStore.users.push({ username, password });
    authStore.currentUser = username;
    return { success: true };
}

export function loginUser(username, password) {
    const user = authStore.users.find(u => u.username === username && u.password === password);
    if (user) {
        authStore.currentUser = user.username;
        return { success: true };
    }
    return { success: false, message: 'Invalid credentials' };
}

export function logout() {
    authStore.currentUser = null;
}