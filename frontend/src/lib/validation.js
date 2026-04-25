export function validateMatchData(match) {
    let errors = { location: '', date: '', time: '' };
    let isValid = true;

    if (!match.location || match.location.trim() === '') {
        errors.location = 'Location is required';
        isValid = false;
    }

    if (!match.date) {
        errors.date = 'Date is required';
        isValid = false;
    } else {
        const today = new Date();
        today.setHours(0, 0, 0, 0);

        const matchDate = new Date(match.date);
        if (matchDate < today) {
            errors.date = 'Cannot schedule a match in the past';
            isValid = false;
        }
    }

    if (!match.time) {
        errors.time = 'Time is required';
        isValid = false;
    }

    return { isValid, errors };
}

export function validateAuthData(username, password) {
    let errors = { username: '', password: '' };
    let isValid = true;

    if (!username || username.trim().length < 3) {
        errors.username = 'Username must be at least 3 characters long';
        isValid = false;
    }

    if (!password || password.trim().length < 6) {
        errors.password = 'Password must be at least 6 characters long';
        isValid = false;
    }

    return { isValid, errors };
}