import { test, expect } from '@playwright/test';

test.describe('Authentication Flow', () => {
    test('should register a new user successfully', async ({ page }) => {
        await page.goto('http://localhost:5173/register');
        await page.waitForTimeout(1000);

        await page.getByPlaceholder('Username').fill('playwright_user');
        await page.getByPlaceholder('Password').fill('secret1234');
        await page.getByRole('button', { name: 'Register' }).click();

        await expect(page).toHaveURL('http://localhost:5173/');
        await expect(page.locator('.greeting')).toContainText('Hello, playwright_user');
    });

    test('should show validation errors on invalid login', async ({ page }) => {
        await page.goto('http://localhost:5173/login');
        await page.waitForTimeout(1000);

        await page.getByPlaceholder('Username').fill('ab');
        await page.getByPlaceholder('Password').fill('123');
        await page.getByRole('button', { name: 'Login' }).click();

        await expect(page.locator('.field-error').first()).toBeVisible();
    });
});

test.describe('Match Creation and Cookies', () => {
    test('should create a match and remember preference', async ({ page }) => {
        await page.goto('http://localhost:5173/create-match');
        await page.waitForTimeout(1000);

        await page.getByPlaceholder('Location').fill('Playwright Stadium');
        await page.locator('input[type="date"]').fill('2026-10-10');
        await page.locator('input[type="time"]').fill('20:00');
        await page.locator('select').selectOption('7v7');

        await page.getByRole('button', { name: 'Create' }).click();

        await expect(page).toHaveURL(/.*\/matches/);

        const nextButton = page.getByRole('button', { name: 'Next' });
        while (await nextButton.isEnabled()) {
            await nextButton.click();
        }

        await expect(page.locator('.matches-table')).toContainText('Playwright Stadium');

        await page.goto('http://localhost:5173/create-match');
        await page.waitForTimeout(1000);

        await expect(page.locator('select')).toHaveValue('7v7');
    });
});

test.describe('Master-Detail Navigation', () => {
    test('should navigate from match list to detail view', async ({ page }) => {
        await page.goto('http://localhost:5173/matches');
        await page.waitForTimeout(1000);

        await page.locator('a.btn-view').first().click();

        await expect(page).toHaveURL(/.*\/matches\/\d+/);
        await expect(page.locator('.detail-card')).toBeVisible();
    });
});