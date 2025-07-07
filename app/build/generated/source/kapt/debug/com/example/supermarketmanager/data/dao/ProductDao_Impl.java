package com.example.supermarketmanager.data.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.supermarketmanager.data.entities.ProductEntity;
import java.lang.Class;
import java.lang.Double;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
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
public final class ProductDao_Impl implements ProductDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ProductEntity> __insertionAdapterOfProductEntity;

  private final SharedSQLiteStatement __preparedStmtOfDecreaseAvailability;

  public ProductDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfProductEntity = new EntityInsertionAdapter<ProductEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `products` (`id`,`name`,`description`,`pricePerUnit`,`unit`,`availability`,`offer`,`categoryId`,`ingredients`,`nutritionalInfo`,`imageDrawable`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ProductEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getDescription());
        }
        statement.bindDouble(4, entity.getPricePerUnit());
        if (entity.getUnit() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getUnit());
        }
        statement.bindLong(6, entity.getAvailability());
        if (entity.getOffer() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getOffer());
        }
        statement.bindLong(8, entity.getCategoryId());
        if (entity.getIngredients() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getIngredients());
        }
        if (entity.getNutritionalInfo() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getNutritionalInfo());
        }
        if (entity.getImageDrawable() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getImageDrawable());
        }
      }
    };
    this.__preparedStmtOfDecreaseAvailability = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE products SET availability = availability - ? WHERE id = ? AND availability >= ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertAll(final List<ProductEntity> products,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfProductEntity.insert(products);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object decreaseAvailability(final int productId, final int quantity,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDecreaseAvailability.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, quantity);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, productId);
        _argIndex = 3;
        _stmt.bindLong(_argIndex, quantity);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDecreaseAvailability.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getByCategory(final int categoryId,
      final Continuation<? super List<ProductEntity>> $completion) {
    final String _sql = "SELECT * FROM products WHERE categoryId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, categoryId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<ProductEntity>>() {
      @Override
      @NonNull
      public List<ProductEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfPricePerUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "pricePerUnit");
          final int _cursorIndexOfUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "unit");
          final int _cursorIndexOfAvailability = CursorUtil.getColumnIndexOrThrow(_cursor, "availability");
          final int _cursorIndexOfOffer = CursorUtil.getColumnIndexOrThrow(_cursor, "offer");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final int _cursorIndexOfIngredients = CursorUtil.getColumnIndexOrThrow(_cursor, "ingredients");
          final int _cursorIndexOfNutritionalInfo = CursorUtil.getColumnIndexOrThrow(_cursor, "nutritionalInfo");
          final int _cursorIndexOfImageDrawable = CursorUtil.getColumnIndexOrThrow(_cursor, "imageDrawable");
          final List<ProductEntity> _result = new ArrayList<ProductEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ProductEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final double _tmpPricePerUnit;
            _tmpPricePerUnit = _cursor.getDouble(_cursorIndexOfPricePerUnit);
            final String _tmpUnit;
            if (_cursor.isNull(_cursorIndexOfUnit)) {
              _tmpUnit = null;
            } else {
              _tmpUnit = _cursor.getString(_cursorIndexOfUnit);
            }
            final int _tmpAvailability;
            _tmpAvailability = _cursor.getInt(_cursorIndexOfAvailability);
            final String _tmpOffer;
            if (_cursor.isNull(_cursorIndexOfOffer)) {
              _tmpOffer = null;
            } else {
              _tmpOffer = _cursor.getString(_cursorIndexOfOffer);
            }
            final int _tmpCategoryId;
            _tmpCategoryId = _cursor.getInt(_cursorIndexOfCategoryId);
            final String _tmpIngredients;
            if (_cursor.isNull(_cursorIndexOfIngredients)) {
              _tmpIngredients = null;
            } else {
              _tmpIngredients = _cursor.getString(_cursorIndexOfIngredients);
            }
            final String _tmpNutritionalInfo;
            if (_cursor.isNull(_cursorIndexOfNutritionalInfo)) {
              _tmpNutritionalInfo = null;
            } else {
              _tmpNutritionalInfo = _cursor.getString(_cursorIndexOfNutritionalInfo);
            }
            final String _tmpImageDrawable;
            if (_cursor.isNull(_cursorIndexOfImageDrawable)) {
              _tmpImageDrawable = null;
            } else {
              _tmpImageDrawable = _cursor.getString(_cursorIndexOfImageDrawable);
            }
            _item = new ProductEntity(_tmpId,_tmpName,_tmpDescription,_tmpPricePerUnit,_tmpUnit,_tmpAvailability,_tmpOffer,_tmpCategoryId,_tmpIngredients,_tmpNutritionalInfo,_tmpImageDrawable);
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

  @Override
  public Object getById(final int productId,
      final Continuation<? super ProductEntity> $completion) {
    final String _sql = "SELECT * FROM products WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, productId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ProductEntity>() {
      @Override
      @Nullable
      public ProductEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfPricePerUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "pricePerUnit");
          final int _cursorIndexOfUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "unit");
          final int _cursorIndexOfAvailability = CursorUtil.getColumnIndexOrThrow(_cursor, "availability");
          final int _cursorIndexOfOffer = CursorUtil.getColumnIndexOrThrow(_cursor, "offer");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final int _cursorIndexOfIngredients = CursorUtil.getColumnIndexOrThrow(_cursor, "ingredients");
          final int _cursorIndexOfNutritionalInfo = CursorUtil.getColumnIndexOrThrow(_cursor, "nutritionalInfo");
          final int _cursorIndexOfImageDrawable = CursorUtil.getColumnIndexOrThrow(_cursor, "imageDrawable");
          final ProductEntity _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final double _tmpPricePerUnit;
            _tmpPricePerUnit = _cursor.getDouble(_cursorIndexOfPricePerUnit);
            final String _tmpUnit;
            if (_cursor.isNull(_cursorIndexOfUnit)) {
              _tmpUnit = null;
            } else {
              _tmpUnit = _cursor.getString(_cursorIndexOfUnit);
            }
            final int _tmpAvailability;
            _tmpAvailability = _cursor.getInt(_cursorIndexOfAvailability);
            final String _tmpOffer;
            if (_cursor.isNull(_cursorIndexOfOffer)) {
              _tmpOffer = null;
            } else {
              _tmpOffer = _cursor.getString(_cursorIndexOfOffer);
            }
            final int _tmpCategoryId;
            _tmpCategoryId = _cursor.getInt(_cursorIndexOfCategoryId);
            final String _tmpIngredients;
            if (_cursor.isNull(_cursorIndexOfIngredients)) {
              _tmpIngredients = null;
            } else {
              _tmpIngredients = _cursor.getString(_cursorIndexOfIngredients);
            }
            final String _tmpNutritionalInfo;
            if (_cursor.isNull(_cursorIndexOfNutritionalInfo)) {
              _tmpNutritionalInfo = null;
            } else {
              _tmpNutritionalInfo = _cursor.getString(_cursorIndexOfNutritionalInfo);
            }
            final String _tmpImageDrawable;
            if (_cursor.isNull(_cursorIndexOfImageDrawable)) {
              _tmpImageDrawable = null;
            } else {
              _tmpImageDrawable = _cursor.getString(_cursorIndexOfImageDrawable);
            }
            _result = new ProductEntity(_tmpId,_tmpName,_tmpDescription,_tmpPricePerUnit,_tmpUnit,_tmpAvailability,_tmpOffer,_tmpCategoryId,_tmpIngredients,_tmpNutritionalInfo,_tmpImageDrawable);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getFilteredWithoutAvailability(final Integer categoryId, final Double maxPrice,
      final Continuation<? super List<ProductEntity>> $completion) {
    final String _sql = "\n"
            + "        SELECT * FROM products\n"
            + "        WHERE (? IS NULL OR categoryId = ?)\n"
            + "          AND (? IS NULL OR pricePerUnit <= ?)\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 4);
    int _argIndex = 1;
    if (categoryId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, categoryId);
    }
    _argIndex = 2;
    if (categoryId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, categoryId);
    }
    _argIndex = 3;
    if (maxPrice == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindDouble(_argIndex, maxPrice);
    }
    _argIndex = 4;
    if (maxPrice == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindDouble(_argIndex, maxPrice);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<ProductEntity>>() {
      @Override
      @NonNull
      public List<ProductEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfPricePerUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "pricePerUnit");
          final int _cursorIndexOfUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "unit");
          final int _cursorIndexOfAvailability = CursorUtil.getColumnIndexOrThrow(_cursor, "availability");
          final int _cursorIndexOfOffer = CursorUtil.getColumnIndexOrThrow(_cursor, "offer");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final int _cursorIndexOfIngredients = CursorUtil.getColumnIndexOrThrow(_cursor, "ingredients");
          final int _cursorIndexOfNutritionalInfo = CursorUtil.getColumnIndexOrThrow(_cursor, "nutritionalInfo");
          final int _cursorIndexOfImageDrawable = CursorUtil.getColumnIndexOrThrow(_cursor, "imageDrawable");
          final List<ProductEntity> _result = new ArrayList<ProductEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ProductEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final double _tmpPricePerUnit;
            _tmpPricePerUnit = _cursor.getDouble(_cursorIndexOfPricePerUnit);
            final String _tmpUnit;
            if (_cursor.isNull(_cursorIndexOfUnit)) {
              _tmpUnit = null;
            } else {
              _tmpUnit = _cursor.getString(_cursorIndexOfUnit);
            }
            final int _tmpAvailability;
            _tmpAvailability = _cursor.getInt(_cursorIndexOfAvailability);
            final String _tmpOffer;
            if (_cursor.isNull(_cursorIndexOfOffer)) {
              _tmpOffer = null;
            } else {
              _tmpOffer = _cursor.getString(_cursorIndexOfOffer);
            }
            final int _tmpCategoryId;
            _tmpCategoryId = _cursor.getInt(_cursorIndexOfCategoryId);
            final String _tmpIngredients;
            if (_cursor.isNull(_cursorIndexOfIngredients)) {
              _tmpIngredients = null;
            } else {
              _tmpIngredients = _cursor.getString(_cursorIndexOfIngredients);
            }
            final String _tmpNutritionalInfo;
            if (_cursor.isNull(_cursorIndexOfNutritionalInfo)) {
              _tmpNutritionalInfo = null;
            } else {
              _tmpNutritionalInfo = _cursor.getString(_cursorIndexOfNutritionalInfo);
            }
            final String _tmpImageDrawable;
            if (_cursor.isNull(_cursorIndexOfImageDrawable)) {
              _tmpImageDrawable = null;
            } else {
              _tmpImageDrawable = _cursor.getString(_cursorIndexOfImageDrawable);
            }
            _item = new ProductEntity(_tmpId,_tmpName,_tmpDescription,_tmpPricePerUnit,_tmpUnit,_tmpAvailability,_tmpOffer,_tmpCategoryId,_tmpIngredients,_tmpNutritionalInfo,_tmpImageDrawable);
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

  @Override
  public Object getFilteredWithAvailability(final Integer categoryId, final Double maxPrice,
      final boolean availableOnly, final Continuation<? super List<ProductEntity>> $completion) {
    final String _sql = "\n"
            + "        SELECT * FROM products\n"
            + "        WHERE (? IS NULL OR categoryId = ?)\n"
            + "          AND (? IS NULL OR pricePerUnit <= ?)\n"
            + "          AND availability = ?\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 5);
    int _argIndex = 1;
    if (categoryId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, categoryId);
    }
    _argIndex = 2;
    if (categoryId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, categoryId);
    }
    _argIndex = 3;
    if (maxPrice == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindDouble(_argIndex, maxPrice);
    }
    _argIndex = 4;
    if (maxPrice == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindDouble(_argIndex, maxPrice);
    }
    _argIndex = 5;
    final int _tmp = availableOnly ? 1 : 0;
    _statement.bindLong(_argIndex, _tmp);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<ProductEntity>>() {
      @Override
      @NonNull
      public List<ProductEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfPricePerUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "pricePerUnit");
          final int _cursorIndexOfUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "unit");
          final int _cursorIndexOfAvailability = CursorUtil.getColumnIndexOrThrow(_cursor, "availability");
          final int _cursorIndexOfOffer = CursorUtil.getColumnIndexOrThrow(_cursor, "offer");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final int _cursorIndexOfIngredients = CursorUtil.getColumnIndexOrThrow(_cursor, "ingredients");
          final int _cursorIndexOfNutritionalInfo = CursorUtil.getColumnIndexOrThrow(_cursor, "nutritionalInfo");
          final int _cursorIndexOfImageDrawable = CursorUtil.getColumnIndexOrThrow(_cursor, "imageDrawable");
          final List<ProductEntity> _result = new ArrayList<ProductEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ProductEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final double _tmpPricePerUnit;
            _tmpPricePerUnit = _cursor.getDouble(_cursorIndexOfPricePerUnit);
            final String _tmpUnit;
            if (_cursor.isNull(_cursorIndexOfUnit)) {
              _tmpUnit = null;
            } else {
              _tmpUnit = _cursor.getString(_cursorIndexOfUnit);
            }
            final int _tmpAvailability;
            _tmpAvailability = _cursor.getInt(_cursorIndexOfAvailability);
            final String _tmpOffer;
            if (_cursor.isNull(_cursorIndexOfOffer)) {
              _tmpOffer = null;
            } else {
              _tmpOffer = _cursor.getString(_cursorIndexOfOffer);
            }
            final int _tmpCategoryId;
            _tmpCategoryId = _cursor.getInt(_cursorIndexOfCategoryId);
            final String _tmpIngredients;
            if (_cursor.isNull(_cursorIndexOfIngredients)) {
              _tmpIngredients = null;
            } else {
              _tmpIngredients = _cursor.getString(_cursorIndexOfIngredients);
            }
            final String _tmpNutritionalInfo;
            if (_cursor.isNull(_cursorIndexOfNutritionalInfo)) {
              _tmpNutritionalInfo = null;
            } else {
              _tmpNutritionalInfo = _cursor.getString(_cursorIndexOfNutritionalInfo);
            }
            final String _tmpImageDrawable;
            if (_cursor.isNull(_cursorIndexOfImageDrawable)) {
              _tmpImageDrawable = null;
            } else {
              _tmpImageDrawable = _cursor.getString(_cursorIndexOfImageDrawable);
            }
            _item = new ProductEntity(_tmpId,_tmpName,_tmpDescription,_tmpPricePerUnit,_tmpUnit,_tmpAvailability,_tmpOffer,_tmpCategoryId,_tmpIngredients,_tmpNutritionalInfo,_tmpImageDrawable);
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

  @Override
  public Object search(final String search,
      final Continuation<? super List<ProductEntity>> $completion) {
    final String _sql = "SELECT * FROM products WHERE name LIKE '%' || ? || '%'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (search == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, search);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<ProductEntity>>() {
      @Override
      @NonNull
      public List<ProductEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfPricePerUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "pricePerUnit");
          final int _cursorIndexOfUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "unit");
          final int _cursorIndexOfAvailability = CursorUtil.getColumnIndexOrThrow(_cursor, "availability");
          final int _cursorIndexOfOffer = CursorUtil.getColumnIndexOrThrow(_cursor, "offer");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final int _cursorIndexOfIngredients = CursorUtil.getColumnIndexOrThrow(_cursor, "ingredients");
          final int _cursorIndexOfNutritionalInfo = CursorUtil.getColumnIndexOrThrow(_cursor, "nutritionalInfo");
          final int _cursorIndexOfImageDrawable = CursorUtil.getColumnIndexOrThrow(_cursor, "imageDrawable");
          final List<ProductEntity> _result = new ArrayList<ProductEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ProductEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final double _tmpPricePerUnit;
            _tmpPricePerUnit = _cursor.getDouble(_cursorIndexOfPricePerUnit);
            final String _tmpUnit;
            if (_cursor.isNull(_cursorIndexOfUnit)) {
              _tmpUnit = null;
            } else {
              _tmpUnit = _cursor.getString(_cursorIndexOfUnit);
            }
            final int _tmpAvailability;
            _tmpAvailability = _cursor.getInt(_cursorIndexOfAvailability);
            final String _tmpOffer;
            if (_cursor.isNull(_cursorIndexOfOffer)) {
              _tmpOffer = null;
            } else {
              _tmpOffer = _cursor.getString(_cursorIndexOfOffer);
            }
            final int _tmpCategoryId;
            _tmpCategoryId = _cursor.getInt(_cursorIndexOfCategoryId);
            final String _tmpIngredients;
            if (_cursor.isNull(_cursorIndexOfIngredients)) {
              _tmpIngredients = null;
            } else {
              _tmpIngredients = _cursor.getString(_cursorIndexOfIngredients);
            }
            final String _tmpNutritionalInfo;
            if (_cursor.isNull(_cursorIndexOfNutritionalInfo)) {
              _tmpNutritionalInfo = null;
            } else {
              _tmpNutritionalInfo = _cursor.getString(_cursorIndexOfNutritionalInfo);
            }
            final String _tmpImageDrawable;
            if (_cursor.isNull(_cursorIndexOfImageDrawable)) {
              _tmpImageDrawable = null;
            } else {
              _tmpImageDrawable = _cursor.getString(_cursorIndexOfImageDrawable);
            }
            _item = new ProductEntity(_tmpId,_tmpName,_tmpDescription,_tmpPricePerUnit,_tmpUnit,_tmpAvailability,_tmpOffer,_tmpCategoryId,_tmpIngredients,_tmpNutritionalInfo,_tmpImageDrawable);
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

  @Override
  public Object getByIdList(final List<Integer> ids,
      final Continuation<? super List<ProductEntity>> $completion) {
    final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT * FROM products WHERE id IN (");
    final int _inputSize = ids.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (Integer _item : ids) {
      if (_item == null) {
        _statement.bindNull(_argIndex);
      } else {
        _statement.bindLong(_argIndex, _item);
      }
      _argIndex++;
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<ProductEntity>>() {
      @Override
      @NonNull
      public List<ProductEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfPricePerUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "pricePerUnit");
          final int _cursorIndexOfUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "unit");
          final int _cursorIndexOfAvailability = CursorUtil.getColumnIndexOrThrow(_cursor, "availability");
          final int _cursorIndexOfOffer = CursorUtil.getColumnIndexOrThrow(_cursor, "offer");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final int _cursorIndexOfIngredients = CursorUtil.getColumnIndexOrThrow(_cursor, "ingredients");
          final int _cursorIndexOfNutritionalInfo = CursorUtil.getColumnIndexOrThrow(_cursor, "nutritionalInfo");
          final int _cursorIndexOfImageDrawable = CursorUtil.getColumnIndexOrThrow(_cursor, "imageDrawable");
          final List<ProductEntity> _result = new ArrayList<ProductEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ProductEntity _item_1;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final double _tmpPricePerUnit;
            _tmpPricePerUnit = _cursor.getDouble(_cursorIndexOfPricePerUnit);
            final String _tmpUnit;
            if (_cursor.isNull(_cursorIndexOfUnit)) {
              _tmpUnit = null;
            } else {
              _tmpUnit = _cursor.getString(_cursorIndexOfUnit);
            }
            final int _tmpAvailability;
            _tmpAvailability = _cursor.getInt(_cursorIndexOfAvailability);
            final String _tmpOffer;
            if (_cursor.isNull(_cursorIndexOfOffer)) {
              _tmpOffer = null;
            } else {
              _tmpOffer = _cursor.getString(_cursorIndexOfOffer);
            }
            final int _tmpCategoryId;
            _tmpCategoryId = _cursor.getInt(_cursorIndexOfCategoryId);
            final String _tmpIngredients;
            if (_cursor.isNull(_cursorIndexOfIngredients)) {
              _tmpIngredients = null;
            } else {
              _tmpIngredients = _cursor.getString(_cursorIndexOfIngredients);
            }
            final String _tmpNutritionalInfo;
            if (_cursor.isNull(_cursorIndexOfNutritionalInfo)) {
              _tmpNutritionalInfo = null;
            } else {
              _tmpNutritionalInfo = _cursor.getString(_cursorIndexOfNutritionalInfo);
            }
            final String _tmpImageDrawable;
            if (_cursor.isNull(_cursorIndexOfImageDrawable)) {
              _tmpImageDrawable = null;
            } else {
              _tmpImageDrawable = _cursor.getString(_cursorIndexOfImageDrawable);
            }
            _item_1 = new ProductEntity(_tmpId,_tmpName,_tmpDescription,_tmpPricePerUnit,_tmpUnit,_tmpAvailability,_tmpOffer,_tmpCategoryId,_tmpIngredients,_tmpNutritionalInfo,_tmpImageDrawable);
            _result.add(_item_1);
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
