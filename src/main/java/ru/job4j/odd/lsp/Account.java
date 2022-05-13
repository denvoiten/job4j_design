package ru.job4j.odd.lsp;

public class Account {
    private double amount;
    private String name;

    public Account(double amount, String name) {
        this.amount = amount;
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean deposit(double money) {
        boolean rsl = getAmount() >= money;
        if (rsl) {
            setAmount(getAmount() - money);
        } else {
            throw new IllegalArgumentException("Недостаточно денег на счете");
        }
        return rsl;
    }

    /**
     * Усиление предусловия
     */
    class CompanyAccount extends Account {
        public CompanyAccount(double amount, String name) {
            super(amount, name);
        }

        @Override
        public boolean deposit(double money) {
            boolean rsl = getAmount() >= money;
            if (rsl) {
                setAmount(getAmount() - money);
            } else if (money > 1000) {
                throw new IllegalArgumentException("Запрещенно снимать больше 1000");
            } else {
                throw new IllegalArgumentException("Недостаточно денег на счете");
            }
            return rsl;
        }

        /**
         * Ослабление постусловия
         */
        class HackerAccount extends Account {

            public HackerAccount(double amount, String name) {
                super(amount, name);
            }

            public boolean withdraw(double money) {
                boolean rsl = true;
                if (rsl) {
                    setAmount(getAmount() - money);
                }
                return rsl;
            }
        }
    }
}
