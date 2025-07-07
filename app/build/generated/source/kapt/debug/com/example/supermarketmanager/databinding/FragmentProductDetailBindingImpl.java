package com.example.supermarketmanager.databinding;
import com.example.supermarketmanager.R;
import com.example.supermarketmanager.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentProductDetailBindingImpl extends FragmentProductDetailBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.toolbar, 1);
        sViewsWithIds.put(R.id.ivDetailImage, 2);
        sViewsWithIds.put(R.id.btnFavorite, 3);
        sViewsWithIds.put(R.id.dg_price_card, 4);
        sViewsWithIds.put(R.id.tvDetailName, 5);
        sViewsWithIds.put(R.id.tvDetailPrice, 6);
        sViewsWithIds.put(R.id.tvDetailUnitInfo, 7);
        sViewsWithIds.put(R.id.labelDescription, 8);
        sViewsWithIds.put(R.id.tvDetailDescription, 9);
        sViewsWithIds.put(R.id.labelnutritionalInfo, 10);
        sViewsWithIds.put(R.id.tvDetailnutritionalInfo, 11);
        sViewsWithIds.put(R.id.labelingredients, 12);
        sViewsWithIds.put(R.id.tvDetailingredients, 13);
        sViewsWithIds.put(R.id.labelAvailability, 14);
        sViewsWithIds.put(R.id.tvDetailAvailability, 15);
        sViewsWithIds.put(R.id.tvDetailOfferh, 16);
        sViewsWithIds.put(R.id.tvDetailOffer, 17);
        sViewsWithIds.put(R.id.bottomBar, 18);
        sViewsWithIds.put(R.id.btnDecreaseDetail, 19);
        sViewsWithIds.put(R.id.tvDetailQuantity, 20);
        sViewsWithIds.put(R.id.btnIncreaseDetail, 21);
        sViewsWithIds.put(R.id.btnAddDetail, 22);
    }
    // views
    @NonNull
    private final android.widget.FrameLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentProductDetailBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 23, sIncludes, sViewsWithIds));
    }
    private FragmentProductDetailBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.LinearLayout) bindings[18]
            , (android.widget.Button) bindings[22]
            , (android.widget.ImageButton) bindings[19]
            , (android.widget.ImageButton) bindings[3]
            , (android.widget.ImageButton) bindings[21]
            , (android.widget.LinearLayout) bindings[4]
            , (android.widget.ImageView) bindings[2]
            , (android.widget.TextView) bindings[14]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[12]
            , (android.widget.TextView) bindings[10]
            , (androidx.appcompat.widget.Toolbar) bindings[1]
            , (android.widget.TextView) bindings[15]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[17]
            , (android.widget.TextView) bindings[16]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[20]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[13]
            , (android.widget.TextView) bindings[11]
            );
        this.mboundView0 = (android.widget.FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}