package com.example.supermarketmanager.ui.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J)\u0010\u0016\u001a\u00020\u00132\b\u0010\u0017\u001a\u0004\u0018\u00010\u00152\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b\u00a2\u0006\u0002\u0010\u001cJ\u0016\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\b2\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u001f\u001a\u00020\u00132\u0006\u0010 \u001a\u00020!J\u0016\u0010\"\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010#\u001a\u00020\u0015J\u0006\u0010$\u001a\u00020\u0013J\u0006\u0010%\u001a\u00020\u0013J\u0006\u0010&\u001a\u00020\u0013J\u0006\u0010\'\u001a\u00020\u0013J\u0016\u0010(\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010)\u001a\u00020\u0015R\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00050\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006*"}, d2 = {"Lcom/example/supermarketmanager/ui/viewmodel/ProductViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_products", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/example/supermarketmanager/data/entities/ProductEntity;", "cartItems", "Landroidx/lifecycle/LiveData;", "Lcom/example/supermarketmanager/data/models/ShoppingCartItem;", "getCartItems", "()Landroidx/lifecycle/LiveData;", "dao", "Lcom/example/supermarketmanager/data/dao/ProductDao;", "products", "getProducts", "shoppingListDao", "Lcom/example/supermarketmanager/data/dao/ShoppingListDao;", "addOneToCart", "", "productId", "", "filterProducts", "categoryId", "maxPrice", "", "availableOnly", "", "(Ljava/lang/Integer;Ljava/lang/Double;Ljava/lang/Boolean;)V", "getById", "removeOneFromCart", "searchProducts", "query", "", "setCartQuantity", "quantity", "sortProductsByDiscount", "sortProductsByPrice", "sortProductsByUnitPrice", "sortProductsDefault", "updateCartItemQuantity", "newQuantity", "app_debug"})
public final class ProductViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.example.supermarketmanager.data.dao.ProductDao dao = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.supermarketmanager.data.dao.ShoppingListDao shoppingListDao = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.util.List<com.example.supermarketmanager.data.models.ShoppingCartItem>> cartItems = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.example.supermarketmanager.data.entities.ProductEntity>> _products = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.util.List<com.example.supermarketmanager.data.entities.ProductEntity>> products = null;
    
    public ProductViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.example.supermarketmanager.data.models.ShoppingCartItem>> getCartItems() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.example.supermarketmanager.data.entities.ProductEntity>> getProducts() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.example.supermarketmanager.data.entities.ProductEntity> getById(int productId) {
        return null;
    }
    
    public final void filterProducts(@org.jetbrains.annotations.Nullable
    java.lang.Integer categoryId, @org.jetbrains.annotations.Nullable
    java.lang.Double maxPrice, @org.jetbrains.annotations.Nullable
    java.lang.Boolean availableOnly) {
    }
    
    public final void searchProducts(@org.jetbrains.annotations.NotNull
    java.lang.String query) {
    }
    
    public final void addOneToCart(int productId) {
    }
    
    public final void setCartQuantity(int productId, int quantity) {
    }
    
    public final void removeOneFromCart(int productId) {
    }
    
    public final void updateCartItemQuantity(int productId, int newQuantity) {
    }
    
    public final void sortProductsDefault() {
    }
    
    public final void sortProductsByPrice() {
    }
    
    public final void sortProductsByDiscount() {
    }
    
    public final void sortProductsByUnitPrice() {
    }
}