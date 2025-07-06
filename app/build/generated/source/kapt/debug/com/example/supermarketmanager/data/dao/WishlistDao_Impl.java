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
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.supermarketmanager.data.entities.ProductEntity;
import com.example.supermarketmanager.data.entities.WishlistItemEntity;
import com.example.supermarketmanager.data.models.WishlistProductItem;
import java.lang.Class;
import java.lang.Exception;
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
public final class WishlistDao_Impl implements WishlistDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<WishlistItemEntity> __insertionAdapterOfWishlistItemEntity;

  private final SharedSQLiteStatement __preparedStmtOfRemoveItem;

  public WishlistDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfWishlistItemEntity = new EntityInsertionAdapter<WishlistItemEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `wishlist_items` (`id`,`productId`) VALUES (nullif(?, 0),?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final WishlistItemEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getProductId());
      }
    };
    this.__preparedStmtOfRemoveItem = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM wishlist_items WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object addItem(final WishlistItemEntity item,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfWishlistItemEntity.insert(item);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object removeItem(final int itemId, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfRemoveItem.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, itemId);
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
          __preparedStmtOfRemoveItem.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getAll(final Continuation<? super List<WishlistItemEntity>> $completion) {
    final String _sql = "SELECT * FROM wishlist_items";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<WishlistItemEntity>>() {
      @Override
      @NonNull
      public List<WishlistItemEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfProductId = CursorUtil.getColumnIndexOrThrow(_cursor, "productId");
          final List<WishlistItemEntity> _result = new ArrayList<WishlistItemEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final WishlistItemEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpProductId;
            _tmpProductId = _cursor.getInt(_cursorIndexOfProductId);
            _item = new WishlistItemEntity(_tmpId,_tmpProductId);
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
  public Object getItemByProductId(final int productId,
      final Continuation<? super WishlistItemEntity> $completion) {
    final String _sql = "SELECT * FROM wishlist_items WHERE productId = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, productId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<WishlistItemEntity>() {
      @Override
      @Nullable
      public WishlistItemEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfProductId = CursorUtil.getColumnIndexOrThrow(_cursor, "productId");
          final WishlistItemEntity _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpProductId;
            _tmpProductId = _cursor.getInt(_cursorIndexOfProductId);
            _result = new WishlistItemEntity(_tmpId,_tmpProductId);
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
  public Object getWishlistWithProducts(
      final Continuation<? super List<WishlistProductItem>> $completion) {
    final String _sql = "\n"
            + "    SELECT \n"
            + "        wishlist_items.id AS wishlist_id,\n"
            + "        wishlist_items.productId AS wishlist_productId,\n"
            + "        products.id AS product_id,\n"
            + "        products.name AS product_name,\n"
            + "        products.description AS product_description,\n"
            + "        products.pricePerUnit AS product_pricePerUnit,\n"
            + "        products.unit AS product_unit,\n"
            + "        products.availability AS product_availability,\n"
            + "        products.offer AS product_offer,\n"
            + "        products.categoryId AS product_categoryId,\n"
            + "        products.ingredients AS product_ingredients,\n"
            + "        products.nutritionalInfo AS product_nutritionalInfo,\n"
            + "        products.imageDrawable AS product_imageDrawable\n"
            + "    FROM wishlist_items\n"
            + "    INNER JOIN products ON wishlist_items.productId = products.id\n";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<WishlistProductItem>>() {
      @Override
      @NonNull
      public List<WishlistProductItem> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = 0;
          final int _cursorIndexOfProductId = 1;
          final int _cursorIndexOfId_1 = 2;
          final int _cursorIndexOfName = 3;
          final int _cursorIndexOfDescription = 4;
          final int _cursorIndexOfPricePerUnit = 5;
          final int _cursorIndexOfUnit = 6;
          final int _cursorIndexOfAvailability = 7;
          final int _cursorIndexOfOffer = 8;
          final int _cursorIndexOfCategoryId = 9;
          final int _cursorIndexOfIngredients = 10;
          final int _cursorIndexOfNutritionalInfo = 11;
          final int _cursorIndexOfImageDrawable = 12;
          final List<WishlistProductItem> _result = new ArrayList<WishlistProductItem>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final WishlistProductItem _item;
            final WishlistItemEntity _tmpWishlist;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpProductId;
            _tmpProductId = _cursor.getInt(_cursorIndexOfProductId);
            _tmpWishlist = new WishlistItemEntity(_tmpId,_tmpProductId);
            final ProductEntity _tmpProduct;
            final int _tmpId_1;
            _tmpId_1 = _cursor.getInt(_cursorIndexOfId_1);
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
            _tmpProduct = new ProductEntity(_tmpId_1,_tmpName,_tmpDescription,_tmpPricePerUnit,_tmpUnit,_tmpAvailability,_tmpOffer,_tmpCategoryId,_tmpIngredients,_tmpNutritionalInfo,_tmpImageDrawable);
            _item = new WishlistProductItem(_tmpWishlist,_tmpProduct);
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
