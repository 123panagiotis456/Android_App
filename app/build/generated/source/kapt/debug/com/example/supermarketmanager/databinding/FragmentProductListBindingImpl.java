package com.example.supermarketmanager.databinding;
import com.example.supermarketmanager.R;
import com.example.supermarketmanager.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentProductListBindingImpl extends FragmentProductListBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.toolbar, 1);
        sViewsWithIds.put(R.id.toolbarTitle, 2);
        sViewsWithIds.put(R.id.rvProducts, 3);
        sViewsWithIds.put(R.id.btnFilters, 4);
        sViewsWithIds.put(R.id.ivFilters, 5);
        sViewsWithIds.put(R.id.tvFilters, 6);
        sViewsWithIds.put(R.id.cartBanner, 7);
        sViewsWithIds.put(R.id.tvRemainingAmount, 8);
        sViewsWithIds.put(R.id.progressBar, 9);
        sViewsWithIds.put(R.id.tvCartQuantity, 10);
        sViewsWithIds.put(R.id.tvCartTotal, 11);
        sViewsWithIds.put(R.id.floatingClose, 12);
    }
    // views
    @NonNull
    private final android.widget.FrameLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentProductListBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds));
    }
    private FragmentProductListBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.LinearLayout) bindings[4]
            , (android.widget.LinearLayout) bindings[7]
            , (android.widget.ImageView) bindings[12]
            , (android.widget.ImageView) bindings[5]
            , (android.widget.ProgressBar) bindings[9]
            , (androidx.recyclerview.widget.RecyclerView) bindings[3]
            , (androidx.appcompat.widget.Toolbar) bindings[1]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[11]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[8]
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