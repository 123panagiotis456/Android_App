package com.example.supermarketmanager.data.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.supermarketmanager.data.entities.Converters;
import com.example.supermarketmanager.data.entities.PurchaseHistoryEntity;
import java.lang.Class;
import java.lang.Double;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class PurchaseHistoryDao_Impl implements PurchaseHistoryDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<PurchaseHistoryEntity> __insertionAdapterOfPurchaseHistoryEntity;

  private final Converters __converters = new Converters();

  public PurchaseHistoryDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPurchaseHistoryEntity = new EntityInsertionAdapter<PurchaseHistoryEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `purchase_history` (`id`,`timestamp`,`totalCost`,`productIds`,`prices`,`quantities`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PurchaseHistoryEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getTimestamp());
        statement.bindDouble(3, entity.getTotalCost());
        final String _tmp = __converters.fromIntList(entity.getProductIds());
        if (_tmp == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, _tmp);
        }
        final String _tmp_1 = __converters.fromDoubleList(entity.getPrices());
        if (_tmp_1 == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, _tmp_1);
        }
        final String _tmp_2 = __converters.fromIntList(entity.getQuantities());
        if (_tmp_2 == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, _tmp_2);
        }
      }
    };
  }

  @Override
  public Object insert(final PurchaseHistoryEntity purchaseHistoryEntity,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfPurchaseHistoryEntity.insert(purchaseHistoryEntity);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object getAll(final Continuation<? super List<PurchaseHistoryEntity>> $completion) {
    final String _sql = "SELECT * FROM purchase_history ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<PurchaseHistoryEntity>>() {
      @Override
      @NonNull
      public List<PurchaseHistoryEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfTotalCost = CursorUtil.getColumnIndexOrThrow(_cursor, "totalCost");
          final int _cursorIndexOfProductIds = CursorUtil.getColumnIndexOrThrow(_cursor, "productIds");
          final int _cursorIndexOfPrices = CursorUtil.getColumnIndexOrThrow(_cursor, "prices");
          final int _cursorIndexOfQuantities = CursorUtil.getColumnIndexOrThrow(_cursor, "quantities");
          final List<PurchaseHistoryEntity> _result = new ArrayList<PurchaseHistoryEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PurchaseHistoryEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final double _tmpTotalCost;
            _tmpTotalCost = _cursor.getDouble(_cursorIndexOfTotalCost);
            final List<Integer> _tmpProductIds;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfProductIds)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfProductIds);
            }
            _tmpProductIds = __converters.toIntList(_tmp);
            final List<Double> _tmpPrices;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfPrices)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfPrices);
            }
            _tmpPrices = __converters.toDoubleList(_tmp_1);
            final List<Integer> _tmpQuantities;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfQuantities)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfQuantities);
            }
            _tmpQuantities = __converters.toIntList(_tmp_2);
            _item = new PurchaseHistoryEntity(_tmpId,_tmpTimestamp,_tmpTotalCost,_tmpProductIds,_tmpPrices,_tmpQuantities);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
