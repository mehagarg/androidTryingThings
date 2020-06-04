package com.example.materialshapes

import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.annotation.AttrRes
import com.example.materialshapes.databinding.ActivityMaterialBinding
import com.google.android.material.shape.CutCornerTreatment
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import com.google.android.material.shape.TriangleEdgeTreatment

class MaterialActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMaterialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material)

        binding = ActivityMaterialBinding.inflate(layoutInflater)
        binding.custom.setShapeBackground()

    }

    private fun View.setShapeBackground() {
        val cornerSize = resources.getDimension(R.dimen.corner_size)
        background = MaterialShapeDrawable(
            ShapeAppearanceModel.builder()
                .setAllCornerSizes(cornerSize)
                .setTopLeftCorner(CutCornerTreatment())
                .setBottomRightCorner(ConcaveRoundedCornerTreatment())
                .setTopEdge(TriangleEdgeTreatment(cornerSize, true))
                .setBottomEdge(TriangleEdgeTreatment(cornerSize, true))
                .setLeftEdge(TriangleEdgeTreatment(cornerSize, false))
                .setRightEdge(TriangleEdgeTreatment(cornerSize, false))
                .build()
        ).apply {
            fillColor = ColorStateList.valueOf(getThemeColor(R.attr.colorPrimary))
        }
    }

    private fun getThemeColor(@AttrRes attrId: Int) =
        TypedValue().let { typedValue ->
            theme.resolveAttribute(attrId, typedValue, true)
            typedValue.data
        }

}
