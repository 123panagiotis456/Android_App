package com.example.supermarketmanager.ui.adapter;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0018B=\u0012\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\u0002\u0010\nJ\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\fJ\b\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u000eH\u0016J\u0018\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000eH\u0016J\u0014\u0010\u0016\u001a\u00020\b2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\fR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/example/supermarketmanager/ui/adapter/ShoppingCartAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/example/supermarketmanager/ui/adapter/ShoppingCartAdapter$CartViewHolder;", "items", "", "Lcom/example/supermarketmanager/data/models/ShoppingCartItem;", "onIncreaseClick", "Lkotlin/Function1;", "", "onDecreaseClick", "(Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "currentItems", "", "getItemCount", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "updateData", "newItems", "CartViewHolder", "app_debug"})
public final class ShoppingCartAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.example.supermarketmanager.ui.adapter.ShoppingCartAdapter.CartViewHolder> {
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.example.supermarketmanager.data.models.ShoppingCartItem> items = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.jvm.functions.Function1<com.example.supermarketmanager.data.models.ShoppingCartItem, kotlin.Unit> onIncreaseClick = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.jvm.functions.Function1<com.example.supermarketmanager.data.models.ShoppingCartItem, kotlin.Unit> onDecreaseClick = null;
    
    public ShoppingCartAdapter(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.supermarketmanager.data.models.ShoppingCartItem> items, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.example.supermarketmanager.data.models.ShoppingCartItem, kotlin.Unit> onIncreaseClick, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.example.supermarketmanager.data.models.ShoppingCartItem, kotlin.Unit> onDecreaseClick) {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public com.example.supermarketmanager.ui.adapter.ShoppingCartAdapter.CartViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    com.example.supermarketmanager.ui.adapter.ShoppingCartAdapter.CartViewHolder holder, int position) {
    }
    
    @java.lang.Override
    public int getItemCount() {
        return 0;
    }
    
    public final void updateData(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.supermarketmanager.data.models.ShoppingCartItem> newItems) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.supermarketmanager.data.models.ShoppingCartItem> currentItems() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000eR\u0011\u0010\u0015\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000eR\u0011\u0010\u0017\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000eR\u0011\u0010\u0019\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u000e\u00a8\u0006\u001b"}, d2 = {"Lcom/example/supermarketmanager/ui/adapter/ShoppingCartAdapter$CartViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "btnDecrease", "Landroid/widget/ImageButton;", "getBtnDecrease", "()Landroid/widget/ImageButton;", "btnIncrease", "getBtnIncrease", "descriptionText", "Landroid/widget/TextView;", "getDescriptionText", "()Landroid/widget/TextView;", "imageView", "Landroid/widget/ImageView;", "getImageView", "()Landroid/widget/ImageView;", "nameText", "getNameText", "offerText", "getOfferText", "priceText", "getPriceText", "quantityText", "getQuantityText", "app_debug"})
    public static final class CartViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView nameText = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView priceText = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView descriptionText = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.ImageView imageView = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView offerText = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView quantityText = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.ImageButton btnIncrease = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.ImageButton btnDecrease = null;
        
        public CartViewHolder(@org.jetbrains.annotations.NotNull
        android.view.View view) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getNameText() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getPriceText() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getDescriptionText() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.ImageView getImageView() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getOfferText() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getQuantityText() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.ImageButton getBtnIncrease() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.ImageButton getBtnDecrease() {
            return null;
        }
    }
}