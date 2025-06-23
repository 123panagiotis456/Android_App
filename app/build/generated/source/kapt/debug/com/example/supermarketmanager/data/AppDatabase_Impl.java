package com.example.supermarketmanager.data;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.example.supermarketmanager.data.dao.CategoryDao;
import com.example.supermarketmanager.data.dao.CategoryDao_Impl;
import com.example.supermarketmanager.data.dao.ProductDao;
import com.example.supermarketmanager.data.dao.ProductDao_Impl;
import com.example.supermarketmanager.data.dao.PurchaseHistoryDao;
import com.example.supermarketmanager.data.dao.PurchaseHistoryDao_Impl;
import com.example.supermarketmanager.data.dao.ShoppingListDao;
import com.example.supermarketmanager.data.dao.ShoppingListDao_Impl;
import com.example.supermarketmanager.data.dao.WishlistDao;
import com.example.supermarketmanager.data.dao.WishlistDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile CategoryDao _categoryDao;

  private volatile ProductDao _productDao;

  private volatile ShoppingListDao _shoppingListDao;

  private volatile WishlistDao _wishlistDao;

  private volatile PurchaseHistoryDao _purchaseHistoryDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `categories` (`id` INTEGER NOT NULL, `name` TEXT NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `products` (`id` INTEGER NOT NULL, `name` TEXT NOT NULL, `description` TEXT NOT NULL, `pricePerUnit` REAL NOT NULL, `unit` TEXT NOT NULL, `imageUrl` TEXT NOT NULL, `availability` INTEGER NOT NULL, `offer` TEXT, `categoryId` INTEGER NOT NULL, `ingredients` TEXT, `nutritionalInfo` TEXT, PRIMARY KEY(`id`), FOREIGN KEY(`categoryId`) REFERENCES `categories`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_products_categoryId` ON `products` (`categoryId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `shopping_list_items` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `productId` INTEGER NOT NULL, `quantity` INTEGER NOT NULL, FOREIGN KEY(`productId`) REFERENCES `products`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_shopping_list_items_productId` ON `shopping_list_items` (`productId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `wishlist_items` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `productId` INTEGER NOT NULL, FOREIGN KEY(`productId`) REFERENCES `products`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_wishlist_items_productId` ON `wishlist_items` (`productId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `purchase_history` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `shoppingListId` INTEGER NOT NULL, `timestamp` INTEGER NOT NULL, `totalCost` REAL NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '88971d0f6b2edc594db47e01af9385da')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `categories`");
        db.execSQL("DROP TABLE IF EXISTS `products`");
        db.execSQL("DROP TABLE IF EXISTS `shopping_list_items`");
        db.execSQL("DROP TABLE IF EXISTS `wishlist_items`");
        db.execSQL("DROP TABLE IF EXISTS `purchase_history`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsCategories = new HashMap<String, TableInfo.Column>(2);
        _columnsCategories.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCategories.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCategories = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCategories = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCategories = new TableInfo("categories", _columnsCategories, _foreignKeysCategories, _indicesCategories);
        final TableInfo _existingCategories = TableInfo.read(db, "categories");
        if (!_infoCategories.equals(_existingCategories)) {
          return new RoomOpenHelper.ValidationResult(false, "categories(com.example.supermarketmanager.data.entities.CategoryEntity).\n"
                  + " Expected:\n" + _infoCategories + "\n"
                  + " Found:\n" + _existingCategories);
        }
        final HashMap<String, TableInfo.Column> _columnsProducts = new HashMap<String, TableInfo.Column>(11);
        _columnsProducts.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProducts.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProducts.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProducts.put("pricePerUnit", new TableInfo.Column("pricePerUnit", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProducts.put("unit", new TableInfo.Column("unit", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProducts.put("imageUrl", new TableInfo.Column("imageUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProducts.put("availability", new TableInfo.Column("availability", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProducts.put("offer", new TableInfo.Column("offer", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProducts.put("categoryId", new TableInfo.Column("categoryId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProducts.put("ingredients", new TableInfo.Column("ingredients", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProducts.put("nutritionalInfo", new TableInfo.Column("nutritionalInfo", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysProducts = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysProducts.add(new TableInfo.ForeignKey("categories", "CASCADE", "NO ACTION", Arrays.asList("categoryId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesProducts = new HashSet<TableInfo.Index>(1);
        _indicesProducts.add(new TableInfo.Index("index_products_categoryId", false, Arrays.asList("categoryId"), Arrays.asList("ASC")));
        final TableInfo _infoProducts = new TableInfo("products", _columnsProducts, _foreignKeysProducts, _indicesProducts);
        final TableInfo _existingProducts = TableInfo.read(db, "products");
        if (!_infoProducts.equals(_existingProducts)) {
          return new RoomOpenHelper.ValidationResult(false, "products(com.example.supermarketmanager.data.entities.ProductEntity).\n"
                  + " Expected:\n" + _infoProducts + "\n"
                  + " Found:\n" + _existingProducts);
        }
        final HashMap<String, TableInfo.Column> _columnsShoppingListItems = new HashMap<String, TableInfo.Column>(3);
        _columnsShoppingListItems.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShoppingListItems.put("productId", new TableInfo.Column("productId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShoppingListItems.put("quantity", new TableInfo.Column("quantity", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysShoppingListItems = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysShoppingListItems.add(new TableInfo.ForeignKey("products", "CASCADE", "NO ACTION", Arrays.asList("productId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesShoppingListItems = new HashSet<TableInfo.Index>(1);
        _indicesShoppingListItems.add(new TableInfo.Index("index_shopping_list_items_productId", false, Arrays.asList("productId"), Arrays.asList("ASC")));
        final TableInfo _infoShoppingListItems = new TableInfo("shopping_list_items", _columnsShoppingListItems, _foreignKeysShoppingListItems, _indicesShoppingListItems);
        final TableInfo _existingShoppingListItems = TableInfo.read(db, "shopping_list_items");
        if (!_infoShoppingListItems.equals(_existingShoppingListItems)) {
          return new RoomOpenHelper.ValidationResult(false, "shopping_list_items(com.example.supermarketmanager.data.entities.ShoppingListItemEntity).\n"
                  + " Expected:\n" + _infoShoppingListItems + "\n"
                  + " Found:\n" + _existingShoppingListItems);
        }
        final HashMap<String, TableInfo.Column> _columnsWishlistItems = new HashMap<String, TableInfo.Column>(2);
        _columnsWishlistItems.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWishlistItems.put("productId", new TableInfo.Column("productId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysWishlistItems = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysWishlistItems.add(new TableInfo.ForeignKey("products", "CASCADE", "NO ACTION", Arrays.asList("productId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesWishlistItems = new HashSet<TableInfo.Index>(1);
        _indicesWishlistItems.add(new TableInfo.Index("index_wishlist_items_productId", false, Arrays.asList("productId"), Arrays.asList("ASC")));
        final TableInfo _infoWishlistItems = new TableInfo("wishlist_items", _columnsWishlistItems, _foreignKeysWishlistItems, _indicesWishlistItems);
        final TableInfo _existingWishlistItems = TableInfo.read(db, "wishlist_items");
        if (!_infoWishlistItems.equals(_existingWishlistItems)) {
          return new RoomOpenHelper.ValidationResult(false, "wishlist_items(com.example.supermarketmanager.data.entities.WishlistItemEntity).\n"
                  + " Expected:\n" + _infoWishlistItems + "\n"
                  + " Found:\n" + _existingWishlistItems);
        }
        final HashMap<String, TableInfo.Column> _columnsPurchaseHistory = new HashMap<String, TableInfo.Column>(4);
        _columnsPurchaseHistory.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPurchaseHistory.put("shoppingListId", new TableInfo.Column("shoppingListId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPurchaseHistory.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPurchaseHistory.put("totalCost", new TableInfo.Column("totalCost", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPurchaseHistory = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPurchaseHistory = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPurchaseHistory = new TableInfo("purchase_history", _columnsPurchaseHistory, _foreignKeysPurchaseHistory, _indicesPurchaseHistory);
        final TableInfo _existingPurchaseHistory = TableInfo.read(db, "purchase_history");
        if (!_infoPurchaseHistory.equals(_existingPurchaseHistory)) {
          return new RoomOpenHelper.ValidationResult(false, "purchase_history(com.example.supermarketmanager.data.entities.PurchaseHistoryEntity).\n"
                  + " Expected:\n" + _infoPurchaseHistory + "\n"
                  + " Found:\n" + _existingPurchaseHistory);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "88971d0f6b2edc594db47e01af9385da", "46c5b310c2a634f4c2a471d68e8b5af0");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "categories","products","shopping_list_items","wishlist_items","purchase_history");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    final boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `categories`");
      _db.execSQL("DELETE FROM `products`");
      _db.execSQL("DELETE FROM `shopping_list_items`");
      _db.execSQL("DELETE FROM `wishlist_items`");
      _db.execSQL("DELETE FROM `purchase_history`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(CategoryDao.class, CategoryDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ProductDao.class, ProductDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ShoppingListDao.class, ShoppingListDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(WishlistDao.class, WishlistDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(PurchaseHistoryDao.class, PurchaseHistoryDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public CategoryDao categoryDao() {
    if (_categoryDao != null) {
      return _categoryDao;
    } else {
      synchronized(this) {
        if(_categoryDao == null) {
          _categoryDao = new CategoryDao_Impl(this);
        }
        return _categoryDao;
      }
    }
  }

  @Override
  public ProductDao productDao() {
    if (_productDao != null) {
      return _productDao;
    } else {
      synchronized(this) {
        if(_productDao == null) {
          _productDao = new ProductDao_Impl(this);
        }
        return _productDao;
      }
    }
  }

  @Override
  public ShoppingListDao shoppingListDao() {
    if (_shoppingListDao != null) {
      return _shoppingListDao;
    } else {
      synchronized(this) {
        if(_shoppingListDao == null) {
          _shoppingListDao = new ShoppingListDao_Impl(this);
        }
        return _shoppingListDao;
      }
    }
  }

  @Override
  public WishlistDao wishlistDao() {
    if (_wishlistDao != null) {
      return _wishlistDao;
    } else {
      synchronized(this) {
        if(_wishlistDao == null) {
          _wishlistDao = new WishlistDao_Impl(this);
        }
        return _wishlistDao;
      }
    }
  }

  @Override
  public PurchaseHistoryDao purchaseHistoryDao() {
    if (_purchaseHistoryDao != null) {
      return _purchaseHistoryDao;
    } else {
      synchronized(this) {
        if(_purchaseHistoryDao == null) {
          _purchaseHistoryDao = new PurchaseHistoryDao_Impl(this);
        }
        return _purchaseHistoryDao;
      }
    }
  }
}
