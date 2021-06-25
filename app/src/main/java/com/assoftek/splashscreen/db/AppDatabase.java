package com.assoftek.splashscreen.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import org.jetbrains.annotations.NotNull;

@Database(entities = {User.class, Details.class, Portfolio.class}, version = 3, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    public abstract DetailsDao detailsDao();

    public abstract PortfolioDao portfolioDao();

    private static AppDatabase INSTANCE;

    public static synchronized AppDatabase getDbInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "DATABASE")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .allowMainThreadQueries()
                    .build();
        }

        return INSTANCE;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull @NotNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        private PortfolioDao portfolioDao;

        private PopulateDbAsyncTask(AppDatabase db) {
            portfolioDao = db.portfolioDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            portfolioDao.insertPortfolio(new Portfolio("Portfolio High 1.1", "Equity", 65.00, 247861.94, 12.61, 48066.15, 24.20, 15.73));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio High 1.2", "Fixed Income", 15.00, 57198.91, 1.53, 5845.73, 15.67, 2.35));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio High 1.3", "Real Estate", 10.00,38132.61, 0.80, 3050.61, 10.00, 1.00));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio High 1.4", "Commodities", 10.00, 38132.61, 0.79, 3008.66, 29.69, 2.97));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio High 1.5", "Crypto", 0.00, 0.00, 0.00, 0.00, 0.00, 0.00));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio High 1.6", "Forex", 0.00, 0.00, 0.00, 0.00, 0.00, 0.00));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio High 1.7", "Total", 100.00, 381326.06, 15.73, 59971.15, 22.05, 22.05));

            portfolioDao.insertPortfolio(new Portfolio("Portfolio High 2.1", "Equity", 65.00, 247861.94, 11.82, 45082.27, 23.02, 14.96));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio High 2.2", "Fixed Income", 15.00, 57198.91, 1.66, 6314.76, 17.13, 2.57));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio High 2.3", "Real Estate", 5.00, 19066.30, 0.40, 1525.30, 10.00, 0.50));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio High 2.4", "Commodities", 5.00, 19066.30, 0.46, 1769.35, 21.88, 1.09));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio High 2.5", "Crypto", 5.00, 19066.30, 5.00, 19066.30, 213.00, 10.65));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio High 2.6", "Forex", 5.00, 19066.30, 0.11, 400.39, 0.06, 0.00));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio High 2.7", "Total", 100.00, 381326.06, 19.45, 74158.39, 29.78, 29.78));

            portfolioDao.insertPortfolio(new Portfolio("Portfolio High 3.1", "Equity ", 60.00, 228795.64, 10.93, 41678.94, 24.10, 14.46));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio High 3.2", "Fixed Income", 25.00, 95331.51, 2.48, 9466.42, 12.82, 3.20));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio High 3.3", "Real Estate", 10.00, 38132.61, 1.00, 3813.26, 8.50, 0.85));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio High 3.4", "Commodities",  0.00, 0.00, 0.00, 0.00, 0.00, 0.00));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio High 3.5", "Crypto", 5.00, 19066.30, 5.00, 19066.30, 213.00, 10.65));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio High 3.6", "Forex", 0.00, 0.00, 0.00, 0.00, 0.06, 0.00));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio High 3.7", "Total", 100.00, 381326.06, 19.41, 74024.92, 29.17, 29.17));

            portfolioDao.insertPortfolio(new Portfolio("Portfolio Medium 1.1", "Equity", 50.00, 245675.33, 6.79, 33338.14, 20.20, 10.10));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio Medium 1.2", "Fixed Income", 30.00, 147405.20, 2.38, 11676.95, 6.55, 1.97));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio Medium 1.3", "Real Estate", 10.00, 49135.07, 1.00, 3930.81, 10.00, 1.00));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio Medium 1.4", "Commodities", 10.00, 49135.07, 2.19, 10750.75, 21.88, 2.19));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio Medium 1.5", "Crypto", 0.00, 0.00, 0.00, 0.00, 0.00, 0.00));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio Medium 1.6", "Forex", 0.00, 0.00, 0.00, 0.00, 0.00, 0.00));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio Medium 1.7", "Total", 100.00, 491350.67, 12.15, 59696.65, 15.25,	15.25));

            portfolioDao.insertPortfolio(new Portfolio("Portfolio Medium 2.1", "Equity", 50.00, 245675.33, 6.59, 32365.27, 19.88, 9.94));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio Medium 2.2", "Fixed Income", 25.00, 122837.67, 2.05, 10087.43, 2.80, 0.70));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio Medium 2.3", "Real Estate", 15.00, 73702.60, 1.33, 6510.40, 9.00, 1.35));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio Medium 2.4", "Commodities", 10.00, 49135.07, 0.88, 4326.34, 23.44, 2.34));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio Medium 2.5", "Crypto", 0.00, 0.00, 0.00, 0.00, 0.00, 0.00));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio Medium 2.6", "Forex", 0.00, 0.00, 0.00, 0.00, 0.00, 0.00));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio Medium 2.7", "Total", 100.00, 491350.67, 10.85, 53289.44, 14.33, 14.33));

            portfolioDao.insertPortfolio(new Portfolio("Portfolio Medium 3.1", "Equity", 45.00, 221107.80, 6.94, 34112.02, 21.40, 9.63));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio Medium 3.2", "Fixed Income", 22.00, 108097.15, 1.81, 8910.64, 4.38, 0.96));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio Medium 3.3", "Real Estate", 15.00, 73702.60, 1.34, 6571.82, 9.50, 1.43));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio Medium 3.4", "Commodities", 10.00, 49135.07, 0.93, 4559.73, 21.88, 2.19));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio Medium 3.5", "Crypto", 0.00, 0.00, 0.00, 0.00, 0.00, 0.00));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio Medium 3.6", "Forex", 8.00, 39308.05321, 0.17, 825.47, 0.06, 0.00));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio Medium 3.7", "Total", 100.00, 491350.67, 11.19, 54979.68, 14.21, 14.21));

            portfolioDao.insertPortfolio(new Portfolio("Portfolio Low 1.1", "Equity", 35.00, 148454.77, 4.20, 17823.06, 19.31, 6.76));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio Low 1.2", "Fixed Income", 50.00, 212078.24, 3.88, 16448.79, 5.39, 2.69));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio Low 1.3", "Real Estate", 15.00, 63623.47, 1.20, 5089.88, 10.00, 1.50));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio Low 1.4", "Commodities", 0.00, 0.00, 0.00, 0.00, 0.00, 0.00));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio Low 1.5", "Crypto", 0.00, 0.00, 0.00, 0.00, 0.00, 0.00));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio Low 1.6", "Forex", 0.00, 0.00, 0.00, 0.00, 0.00, 0.00));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio Low 1.7", "Total", 100.00, 424156.49, 9.28, 39361.72, 10.95, 10.95));

            portfolioDao.insertPortfolio(new Portfolio("Portfolio Low 2.1", "Equity", 35.00, 148454.77, 4.49, 19055.23, 19.60, 6.86));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio Low 2.2", "Fixed Income", 50.00, 212078.24, 4.24, 17992.72, 7.72, 3.86));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio Low 2.3", "Real Estate", 15.00, 63623.47, 1.05, 4453.64, 10.00, 1.50));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio Low 2.4", "Commodities", 0.00, 0.00, 0.00, 0.00, 0.00, 0.00));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio Low 2.5", "Crypto", 0.00, 0.00, 0.00, 0.00, 0.00, 0.00));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio Low 2.6", "Forex", 0.00, 0.00, 0.00, 0.00, 0.00, 0.00));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio Low 2.7", "Total", 100.00, 424156.49, 9.78, 41501.59, 12.22, 12.22));
            
            portfolioDao.insertPortfolio(new Portfolio("Portfolio Low 3.1", "Equity", 30.00, 127246.95, 3.65, 15481.71, 18.73, 5.62));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio Low 3.2", "Fixed Income", 55.00, 233286.07, 5.09, 21606.53, 9.78, 5.38));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio Low 3.3", "Real Estate", 15.00, 63623.47, 1.13, 4771.76, 10.00, 1.50));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio Low 3.4", "Commodities", 0.00, 0.00, 0.00, 0.00, 0.00, 0.00));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio Low 3.5", "Crypto", 0.00, 0.00, 0.00, 0.00, 0.00, 0.00));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio Low 3.6", "Forex", 0.00, 0.00, 0.00, 0.00, 0.00, 0.00));
            portfolioDao.insertPortfolio(new Portfolio("Portfolio Low 3.7", "Total", 100.00, 424156.49, 9.87, 41860.00, 12.50, 12.50));

            return null;
        }
    }

}
